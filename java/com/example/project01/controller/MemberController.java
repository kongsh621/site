package com.example.project01.controller;

import com.example.project01.domain.*;
import com.example.project01.service.MemberService;
import com.example.project01.service.RedirectMessage;
import com.example.project01.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Map;

@Slf4j
@RequestMapping("/member/")
@Controller
public class MemberController {
    @Autowired
    private MemberService memberService;

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RedirectMessage redirectMessage;

    @Autowired
    private HomeController homeController;


    @GetMapping("/list")
    public String list(Model model, Criteria criteria, @SessionAttribute(name = "loginMember", required = false) MemberDTO loginMember) {
        model.addAttribute("loginSession", loginMember);
        long total = memberService.getTotal(criteria);
        model.addAttribute("memberList", memberService.getList());
        model.addAttribute("pageMaker", new PageMaker(criteria, total));
        return "memberList";
    }

    @GetMapping("/save")
    public String register(@ModelAttribute MemberDTO memberDTO, @SessionAttribute(name = "loginMember", required = false) MemberDTO loginMember,
                           Model model) {
        model.addAttribute("loginSession", loginMember);
        if (memberDTO.getEmail() == null) {
            model.addAttribute("member", new MemberDTO());
        }
        return "member/memberSave";
    }

    @PostMapping("/save")
    public String register(@ModelAttribute MemberDTO member, Model model, RedirectAttributes redirectAttributes,
                           @SessionAttribute(name = "loginMember", required = false) MemberDTO loginMember) {
        model.addAttribute("loginSession", loginMember);

        try {// 비밀번호 암호화해 저장
            boolean result = userService.registerUser(member);
            if (!result) {
                return redirectMessage.sendRedirectExceptId(redirectAttributes, "회원가입에 실패하였습니다", "/member/save");
            }
        } catch (Exception e) {
            return redirectMessage.sendRedirectExceptId(redirectAttributes, "시스템에 문제가 발생했습니다", "/member/save");
        }
        return redirectMessage.sendRedirect(redirectAttributes, member.getId(), null, "회원가입 되었습니다", "/member/login");
    }

    // 아이디 중복 확인 버튼
    @ResponseBody
    @PostMapping("/idcheck")
    public ResponseEntity<?> idCheck(@RequestBody MemberDTO member, Model model) {
        boolean isValidEmail = userService.isValidEmail(member.getEmail());
        System.out.println("중복확인 email = " + member.getEmail());
        String message;

        if (!isValidEmail) {
            message = "사용할 수 없는 아이디입니다.";
            return ResponseEntity.ok(new ResponseMessage(message));
        } else if (isValidEmail){
            message = "사용 가능한 아이디입니다.";
            return ResponseEntity.ok(new ResponseMessage(message));
        }
        else {
            message = "오류";
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(message));
        }
    }

    // 아이디 중복 확인 응답 메시지를 담을 객체
    public static class ResponseMessage{
        private String message;

        ResponseMessage(String message){
            this.message = message;
            System.out.println("message" + message);
        }
        public String getMessage() { return message; }
        public void setMessage(String message) { this.message = message; }
    }

    @GetMapping("/login")
    public String openLoginPage(@SessionAttribute(name = "loginMember", required = false) MemberDTO loginMember,
                                Model model) {
        model.addAttribute("loginSession", loginMember);
        return "siteLogin";
    }

    @PostMapping("/login")
    public String loginSuccess(@SessionAttribute(name = "loginMember", required = false) MemberDTO loginMember,
                               @ModelAttribute MemberDTO memberDTO, HttpServletRequest request,
                               @RequestParam(value = "saveId", required = false) String saveId,
                               HttpServletResponse response, Model model, RedirectAttributes ra) {
        model.addAttribute("loginSession", loginMember);

        // 아이디 저장 버튼 클릭시 쿠키에 담아 보관
        Cookie cookie = new Cookie("saveId", memberDTO.getEmail());
        cookie.setPath("/"); // 쿠키를 담아줄 경로 -> 메인 페이지 + 모든 하위 주소 포함

        if (saveId != null) { // 아이디 저장 체크시
            cookie.setMaxAge(60 * 60 * 24 * 30); // 30일 저장 (초 단위)
        } else cookie.setMaxAge(0); // 쿠키 삭제
        // 브라우저로 보내준다
        response.addCookie(cookie);

        try {
            MemberDTO member = memberService.selectMember(memberDTO); // 해당 아이디의 정보 가져옴
            boolean isAuthenticated = userService.loginAuthenticate(memberDTO.getEmail(), memberDTO.getPassword());

            if (isAuthenticated){
                HttpSession session = request.getSession();
                String redirectUrl = userService.getRedirectUrlAfterLogin(session);

                if (redirectUrl.length() > 21){ // URI로 담아오는 경우도 있어서
                    redirectUrl = redirectUrl.substring(21);
                }

                session.setAttribute("loginMember", member); // 세션에 로그인 정보 저장
                return redirectMessage.sendRedirectExceptId(ra, "로그인에 성공했습니다", redirectUrl);
            } else {
                return redirectMessage.sendRedirectExceptId(ra, "로그인에 실패했습니다.", "/member/login");
            }
        } catch (Exception e){
            return redirectMessage.sendRedirectExceptId(ra, "시스템에 문제가 발생했습니다", "/member/login");
        }
    }

    // 로그인 전 페이지 저장
    @ResponseBody
    @PostMapping("/pageurl")
    public ResponseEntity<?> handlePageUrl(@RequestBody Map<String, Object> request, HttpSession session){
        Map<String, Object> returnUrl = userService.savePreLoginUrl(session, request);
        if (returnUrl.get("status").equals("success"))
            System.out.println("returnUrl = " + returnUrl.get("status"));
        else System.out.println("returnUrl = " + returnUrl.get("status"));

        return ResponseEntity.ok(returnUrl);
    }

    // 카카오톡 로그인 연동
    @GetMapping("/auth/kakao/callback")
    public String kakaoCallback(String code, HttpServletRequest request, Model model, RedirectAttributes ra, Criteria criteria){
        // POST 방식으로 key-value 데이터를 요청 (카카오 쪽으로)
        RestTemplate rt = new RestTemplate();

        // HTTP Header 오브젝트
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        // HTTP Body 오브젝트
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();

        String grant_type = "authorization_code";
        String client_id = "임시";
        String redirect_uri = "http://localhost:8080/member/auth/kakao/callback";

        // 필요한 정보들 담아줌
        params.add("grant_type", grant_type);
        params.add("client_id", client_id);
        params.add("redirect_uri", redirect_uri);
        params.add("code", code);

        // HTTP Header와 Body를 한 오브젝트에 담기
        HttpEntity<MultiValueMap<String, String>> kakaoTokenRequest =
            new HttpEntity<>(params, headers);

        // HTTP 요청 후 response 의 응답 받음
        ResponseEntity<String> response = rt.exchange(
            "https://kauth.kakao.com/oauth/token",
            HttpMethod.POST,
            kakaoTokenRequest,
            String.class
        );

        ObjectMapper objectMapper = new ObjectMapper();
        OAuthToken oauthToken = null; // try 안에 넣어두면 다른 곳에서 못 쓰니까 밖에서 선언, 초기화
        try {
            oauthToken = objectMapper.readValue(response.getBody(), OAuthToken.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        System.out.println("카카오 액세스 토큰-------------" + oauthToken.getAccess_token());

        // 액세스 토큰을 세션에 저장 (탈퇴시 필요함)
        HttpSession session = request.getSession();
        session.setAttribute("accessToken", oauthToken.getAccess_token());

        // 이번엔 토큰 담아서 정보 요청
        RestTemplate rt2 = new RestTemplate();

        HttpHeaders headers2 = new HttpHeaders();
        headers2.add("Authorization", "Bearer " + oauthToken.getAccess_token());
        headers2.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        HttpEntity<MultiValueMap<String, String>> kakaoProfileRequest2 =
            new HttpEntity<>(headers2); //이미 오버로딩이 되어있어서 param 안 넣고 헤더만 넣어도 만들어진다

        ResponseEntity<String> response2 = rt2.exchange(
            "https://kapi.kakao.com/v2/user/me",
            HttpMethod.POST,
            kakaoProfileRequest2,
            String.class
        );

        System.out.println("카카오 정보 요청" + response2.getBody());

        ObjectMapper objectMapper2 = new ObjectMapper();
        KakaoProfile kakaoProfile = null;
        try {
            kakaoProfile = objectMapper2.readValue(response2.getBody(), KakaoProfile.class);
        } catch (JsonProcessingException e) { // ObjectMapper 예외 처리
            throw new RuntimeException(e);
        }

        try {
            MemberDTO kakaoUser = userService.setUserForKakao(kakaoProfile);
            boolean newUser = userService.isValidEmailForKakao(kakaoProfile);

            // 가입자 혹은 비가입자 체크해서 처리
            // 비가입자는 회원가입부터
            if (newUser){ // 비가입자
                memberService.register(kakaoUser);
                // 회원가입 폼으로 연결해서 해도 되지만 나는 이메일을 못 받아오기 때문에
                // 그냥 바로 회원가입으로 연결
                System.out.println("비가입자 회원가입 완료 = " + kakaoUser);
            }

            // 가입자라면 바로 로그인 처리, 비가입자라면 회원가입 후 로그인 처리
            // 비밀번호는 가입 시 랜덤으로 만들어 암호화 후 저장 => 검사할 때만 사용하고 변경, 조회는 불가
            MemberDTO user = memberService.selectMember(kakaoUser);
            String email = user.getEmail();
            String pw = user.getPassword();

            boolean loginAuth = userService.loginAuthenticateForKakao(email,pw);
            if (loginAuth){
                String redirectUrl = userService.getRedirectUrlAfterLogin(session);
                session.setAttribute("loginMember", memberService.selectMember(kakaoUser)); // 세션에 로그인 정보 저장
                return redirectMessage.sendRedirectExceptId(ra, "로그인 되었습니다.", "redirect:" + redirectUrl);
            }
            return redirectMessage.sendRedirectExceptId(ra, "로그인에 실패했습니다.", "/member/login");
        } catch (Exception e){
            return redirectMessage.sendRedirectExceptId(ra, "시스템에 문제가 발생했습니다", "/member/login");
        }
    }

    @GetMapping("/update")
    public String update(@SessionAttribute(name = "loginMember", required = false) MemberDTO loginMember,
                         Model model) {
        model.addAttribute("loginSession", loginMember);
        model.addAttribute("member", loginMember);

        if (loginMember.getEmail() != null && loginMember.getEmail().toLowerCase().contains("kakao.login")) {
            model.addAttribute("kakaoLogin", loginMember);
            // 카카오 계정일 땐 비밀번호 변경할 수 없고 수정 가능 내용이 달라서 모델 객체로 보내준다.
            System.out.println("카카오 계정");
        }
        return "member/memberUpdate";
    }

    @PostMapping("/update")
    public String update(@SessionAttribute(name = "loginMember", required = false) MemberDTO loginMember, Model model,
                         @ModelAttribute MemberDTO member, RedirectAttributes ra, HttpSession session,
                         Criteria criteria) {
        model.addAttribute("loginSession", loginMember);

        try {
            // 카카오 계정은 닉네임, 이름, 나이 수정 가능
            if (loginMember.getEmail() != null && loginMember.getEmail().toLowerCase().contains("kakao.login")) {
                loginMember.setName(member.getName());
                loginMember.setNickname(member.getNickname());
                loginMember.setAge(member.getAge());
                boolean result = memberService.updateKakao(loginMember);

                if (result){
                    session.setAttribute("loginMember", loginMember);
                    return redirectMessage.sendRedirect(ra, loginMember.getId(), criteria, "업데이트 되었습니다.", "/mypage/");
                }
            }

            // 기존 정보에 입력한 정보를 추가
            loginMember.setEmail(member.getEmail());
            loginMember.setNickname(member.getNickname());
            loginMember.setName(member.getName());
            loginMember.setAge(member.getAge());
            boolean result = memberService.update(loginMember);

            if (result){
                ra.addFlashAttribute("result", new ResultDTO(true, "update"));
                session.setAttribute("loginMember", loginMember);
                return redirectMessage.sendRedirectExceptId(ra, "업데이트 되었습니다.", "/mypage/");
            } else
                return redirectMessage.sendRedirectExceptId(ra, "업데이트에 실패하였습니다.", "/mypage/");
        } catch (Exception e){
            return redirectMessage.sendRedirectExceptId(ra, "시스템에 문제가 발생했습니다", "/mypage/");
        }
    }

    @RequestMapping("/delete")
    public String deleteAccount(HttpServletRequest request, @RequestParam Long id, RedirectAttributes ra, Model model,
                                @SessionAttribute(name = "loginMember", required = false) MemberDTO loginMember,
                                HttpSession session){
        model.addAttribute("loginSession", loginMember);

        if (memberService.delete(id)) {
            System.out.println("탈퇴 완료");
            session.invalidate();
            return redirectMessage.sendRedirectExceptId(ra, "탈퇴 처리가 완료되었습니다.", "/");
        }
        else
            return redirectMessage.sendRedirectExceptId(ra, "탈퇴 처리가 완료되지 않았습니다.", "/");
    }

    // 카카오 회원 탈퇴 + 탈퇴시 알림
    @ResponseBody
    @GetMapping("/notifyDeleted")
    public ResponseEntity<String> notifyUserDeleted(@SessionAttribute(name = "loginMember", required = false) MemberDTO loginMember) {
        if (loginMember == null) {
            // 로그인된 사용자가 없는 경우 오류 메시지
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("로그인되어 있지 않습니다.");
        }

        System.out.println("탈퇴하려는 카카오 유저 id: " + loginMember.getId());
        Long deletedUser = loginMember.getId();

        boolean isDeleted = memberService.delete(deletedUser);
        if (isDeleted) {
            System.out.println("사이트에서의 id = " + deletedUser + " 탈퇴 처리가 완료되었습니다.");
            return ResponseEntity.ok("탈퇴 처리가 완료되었습니다.");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("시스템에 문제가 발생했습니다.");
        }
    }

    //----------------찾기------------------
    // 아이디 찾기
    @GetMapping("/idfind")
    public String idFindForm(HttpSession session, Model model) {
        return "member/idfind";
    }

    @PostMapping("/idfind")
    public String idFindCheck(@SessionAttribute(name = "loginMember", required = false) MemberDTO loginMember, Model model,
                              @RequestParam String name, RedirectAttributes ra, HttpSession session) {
        model.addAttribute("loginSession", loginMember);

        String result = "";
        try {
            result = memberService.findByName(name);
            System.out.println("Email = " + result);

            if (result == null) {
                return redirectMessage.sendRedirectExceptId(ra, "아이디가 존재하지 않습니다.", "/member/idfind");
            } else {
                model.addAttribute("idCheck", result);
                return "member/idfind";
            }
        } catch (Exception e) {
            return redirectMessage.sendRedirectExceptId(ra, "시스템에 문제가 발생했습니다.", "/member/idfind");
        }
    }

    // 비밀번호 찾기
    @GetMapping("/passfind")
    public String passFindForm(@SessionAttribute(name = "loginMember", required = false) MemberDTO loginMember, Model model) {
        model.addAttribute("loginSession", loginMember);
        return "member/passfind";
    }

    @PostMapping("/passfind")
    public String passFindCheck(@SessionAttribute(name = "loginMember", required = false) MemberDTO loginMember,
                                @ModelAttribute MemberDTO memberDTO, Model model, RedirectAttributes ra,
                                @RequestParam("inputCode") String input, HttpSession session,
                                @SessionAttribute(name = "mailedPass", required = false) String mailedPass) {
        model.addAttribute("loginSession", loginMember);

        MemberDTO result = memberService.selectMember(memberDTO);

        try {
            if (!input.equals(mailedPass)) {
                return redirectMessage.sendRedirectExceptId(ra, "잘못된 인증번호입니다.", "/member/passfind");
            } else {
                session.setAttribute("updatePass", result);
                return redirectMessage.sendRedirectExceptId(ra, "인증에 성공했습니다. 새로운 비밀번호로 변경해 주세요.",
                                                        "/member/setnewpass");
            }
        }
        catch (Exception e){
            return redirectMessage.sendRedirectExceptId(ra, "시스템에 문제가 발생했습니다.", "/member/passfind");
        }
    }

    // 비밀번호 변경 (비밀번호 찾기)
    @GetMapping("/setnewpass")
    public String setNewPasswordForm() {
        return "member/setnewpass";
    }

    @PostMapping("/setnewpass")
    public String setNewPass(@SessionAttribute(name = "updatePass", required = false) MemberDTO updatePass,
                             @ModelAttribute PassCheck passCheck, RedirectAttributes ra, HttpSession session) {
        if (updatePass == null)
             return "redirect:/";
        try{
            String newPw = passwordEncoder.encode(passCheck.getNewPass()); // 새 비밀번호 인코딩

            // 새 비밀번호 인코딩 후 담아서 업데이트
            boolean result = memberService.updatePass(new MemberDTO(updatePass.getEmail(), newPw));

            if (passwordEncoder.matches(passCheck.getNewPass(), updatePass.getPassword())){
                return redirectMessage.sendRedirectExceptId(ra, "기존 비밀번호와 다른 비밀번호를 입력해 주세요.", "/member/setnewpass");
            } else if (!passCheck.getNewPass().equals(passCheck.getNewPassCheck())){
                return redirectMessage.sendRedirectExceptId(ra, "비밀번호가 일치하지 않습니다.", "/member/setnewpass");
            } else if (!result) {
                return redirectMessage.sendRedirectExceptId(ra, "비밀번호 변경에 실패하였습니다.", "/member/passfind");
            } else
                session.removeAttribute("updatePass");
                return redirectMessage.sendRedirectExceptId(ra, "변경된 비밀번호로 로그인 해주시기 바랍니다.", "/member/login");
        } catch (Exception e) {
            return redirectMessage.sendRedirectExceptId(ra, "시스템에 문제가 발생했습니다.", "/member/passfind");
        }
    }
}
