package com.example.project01.service;

import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.io.UnsupportedEncodingException;
import java.util.Random;

@Service
public class MailServiceImpl implements MailService {
    @Autowired
    private JavaMailSender emailSender; // MailConfig 에서 등록한 Bean

    private String ePw; // 인증번호

    @Override
    public MimeMessage createMessage(String to) throws MessagingException, UnsupportedEncodingException {
        // 보낼 MimeMessage 생성
        MimeMessage message = emailSender.createMimeMessage();

        message.addRecipients(Message.RecipientType.TO, to);
        message.setSubject("비밀번호 찾기를 위한 이메일 인증번호입니다.");

        String msg = "";
        msg += "<h1>안녕하세요. 뭐 볼래?입니다.</h1>";
        msg += "<p>아래 인증번호를 창에 입력해주세요.</p>";
        msg += "<br>";
        msg += "<br>";
        msg += "<strong>" + ePw + "</strong>";

        message.setText(msg, "UTF-8", "html");
        message.setFrom(new InternetAddress("kongjy621@naver.com", "뭐 볼래?"));

        return message;
    }

    @Override
    public String createKey() { // 랜덤 인증코드 생성
        int leftLimit = 48; // 0
        int rightLimit = 122; // z
        int targetStringLength = 10; 
        Random random = new Random(); // 랜덤 클래스를 이용해 인증번호 생성
        String key = random.ints(leftLimit, rightLimit + 1) // 범위 제한 (intstream을 반환)
                            .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97)) // 0~9 / a~z / A~Z 조합
                            .limit(targetStringLength) // 문자열 크기
                            .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append) // StringBuilder 객체 생성
                            .toString();

        return key;
    }

    @Override
    public String sendSimpleMessage(String to) throws Exception {
        ePw = createKey();
        MimeMessage message = createMessage(to);

        try {
            emailSender.send(message);
        } catch (Exception e){
            e.printStackTrace();
            throw new IllegalArgumentException();
        }
        return ePw; // 서버로 반환하여 사용자가 입력한 코드와의 일치여부 확인
    }
}
