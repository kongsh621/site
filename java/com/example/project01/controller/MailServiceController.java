package com.example.project01.controller;

import com.example.project01.service.MailService;
import com.example.project01.service.MailServiceImpl;
import com.oracle.wls.shaded.org.apache.xpath.operations.Mod;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@RestController
@RequestMapping(value="/api/mail")
public class MailServiceController {
    @Autowired
    MailServiceImpl registerMail;

    @PostMapping(value = "/confirm/json")
    public String mailConfirm(@RequestParam(name ="email") String email, HttpSession session) throws Exception {
        String code = registerMail.sendSimpleMessage(email);
        session.setAttribute("mailedPass", code); // 세션에 인증번호 저장
        System.out.println(code);
        return code;
    }
}
