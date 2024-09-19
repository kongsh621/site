package com.example.project01.service;

import com.example.project01.domain.KakaoProfile;
import com.example.project01.domain.MemberDTO;
import com.example.project01.mapper.MemberMapper;
import jakarta.servlet.http.HttpSession;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.regex.Pattern;

@Service
public class UserService {

    private final MemberMapper mapper;

//    @Resource(name="bcryptPasswordEncoder") // 이름에 따라 주입하도록 하는 어노테이션 / Autowired는 타입에 따라
    private final BCryptPasswordEncoder passwordEncoder;

    // 이메일 정규식 패턴
    private static final String EMAIL_REGEX = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";

     // 정규식 패턴 객체
    private static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);

    public UserService(MemberMapper mapper, BCryptPasswordEncoder passwordEncoder) {
        this.mapper = mapper;
        this.passwordEncoder = passwordEncoder;
    }

    public Map<String, Object> savePreLoginUrl(HttpSession session, Map<String, Object>pageUrl){
        HashMap<String, Object> response = new HashMap<>();
        if (pageUrl != null && !pageUrl.containsValue("/login")){
            session.setAttribute("preLoginUrl", pageUrl.get("pageUrl"));
            System.out.println("pageUrl.get(\"pageUrl\") = " + pageUrl.get("pageUrl"));
            response.put("status", "success");
        } else {
            response.put("status", "failure");
        }
        return response;
    }

    public String getRedirectUrlAfterLogin(HttpSession session){
        String preLoginUrl = (String) session.getAttribute("preLoginUrl");
        if (preLoginUrl == null || preLoginUrl.contains("/login")){
            return  "/index";
        }
        session.removeAttribute("preLoginUrl");
        return preLoginUrl;
    }

    public String getRedirectUrlAfterLogout(HttpSession session){
        String preLoginUrl = (String) session.getAttribute("preLoginUrl");
        if (preLoginUrl == null || preLoginUrl.contains("/login")){
            return  "/index";
        }
        session.removeAttribute("preLoginUrl");
        return preLoginUrl;
    }
    public boolean loginAuthenticate(String email, String password){
        //  로그인 사용자 인증 로직
        MemberDTO member = new MemberDTO();
        if (email == null || password == null)
            return false;

        // 이메일이 존재하면 인코딩 된 pw를 불러와서 비교
        String encodePw = mapper.findByEmail(email).getPassword();
        if (passwordEncoder.matches(password, encodePw)){
            return true;
        } else {
            System.out.println("로그인 패스워드 인증 실패");
            return false;
        }
    }

    // 이메일 검사 메서드
    public boolean isValidEmail(String email) {
        if (email == null) { // null 확인
            return false;
        }
        if (mapper.findByEmail(email) != null){ // 중복 확인
            return false;
        }
        return EMAIL_PATTERN.matcher(email).matches(); // 가입 검사 로직
    }


    public boolean registerUser(MemberDTO memberDTO){
        if (memberDTO == null){
            System.out.println("회원가입에 실패했습니다. memberDTO = " + memberDTO);
            return false;
        
        // 비밀번호 인코딩 후 가입
        memberDTO.setPassword(passwordEncoder.encode(memberDTO.getPassword()));
        return mapper.save(memberDTO) == 1; // 암호화 해서 저장

    }

    // 카카오 이메일 검사 메서드
    public boolean isValidEmailForKakao(KakaoProfile kakaoProfile) {
        if (kakaoProfile.getId() == null) { // null 확인
            throw new IllegalArgumentException("이메일이 올바르지 않습니다.");
        }
        return mapper.findByEmail(kakaoProfile.getId() + "@kakao.login") == null; // 존재하는 계정이면 false
    }

    // 카카오 회원가입
    public MemberDTO setUserForKakao(KakaoProfile kakaoProfile){
//        UUID garbagePassword = UUID.randomUUID(); // 랜덤
//        근데 시큐리티 암호화랑 연결해야 하는데 UUID는 길이가 36자리라 이렇게 하면 너무 길어져서 Random 클래스 사용
//        하지만 Random은 UUID과 달리 중복 가능성 문제가 있다.
        Random random = new Random();
        int pass = random.nextInt(1000) + 1000; //1000~2000 사이 값으로 설정

        // memberVO 오브젝트 : email, password, nickname, name, age
        MemberDTO kakaoUser = new MemberDTO();
        kakaoUser.setEmail(kakaoProfile.getId() + "@kakao.login"); // email을 받아오지 못하는데 아이디를 이메일 형식으로 할 거라 임시 계정 생성
        kakaoUser.setPassword(passwordEncoder.encode(Integer.toString(pass))); 
        kakaoUser.setNickname(kakaoProfile.getProperties().getNickname());
        kakaoUser.setName(""); // 임시
        kakaoUser.setAge(0); // 임시

        return kakaoUser;
    }
    // 카카오 로그인 인증 로직
    // 비밀번호가 랜덤생성이라 email로만 검증 가능
    public boolean loginAuthenticateForKakao(String email, String password){
        //  로그인 사용자 인증 로직
        MemberDTO member = new MemberDTO();
        if (email == null || password == null)
            return false;

        if (mapper.findByEmail(email) == null) {
            System.out.println("존재하지 않는 아이디 -> 회원가입으로 연결"); 
            return false;
        }
        System.out.println("로그인 패스워드 검증 완료");
        return true;
    }
}
