package com.example.project01.controller;

import com.example.project01.domain.*;
import com.example.project01.service.BoardService;
import com.example.project01.service.MemberService;
import com.example.project01.service.MyPageService;
import com.oracle.wls.shaded.org.apache.xpath.operations.Mod;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/mypage/")
public class MyPageController {
    @Autowired
    private MemberService memberService;
    @Autowired
    private MyPageService myPageService;

    @GetMapping("/")
    public String index(@ModelAttribute MemberVO memberVO, @SessionAttribute(name="loginMember", required = false) MemberVO loginMember,
                        Model model, HttpServletRequest request){
        // 로그인 상태 아니라면 메인으로 리다이렉트
        if (loginMember == null)
            return "redirect:/";
        model.addAttribute("loginSession", loginMember);
        model.addAttribute("member", loginMember);
        System.out.println("로그인 된 아이디 " + loginMember);

        if (loginMember.getEmail() != null && loginMember.getEmail().toLowerCase().contains("kakao.login")) {
            model.addAttribute("kakaoLogin", loginMember);
            // 카카오 계정일 땐 비밀번호 변경할 수 없고 탈퇴 버튼이 달라서 모델 객체로 보내준다.
            System.out.println("카카오 계정");
            // 탈퇴시 필요한 액세스토큰
            HttpSession session = request.getSession();
            String Access_Token = (String)session.getAttribute("accessToken");
            model.addAttribute("Access_Token", Access_Token);
        }

        return "mypage/details";
    }

    // 비밀번호 변경 (마이페이지)
    @GetMapping("/setpass")
    public String setPassForm(@SessionAttribute(name = "loginMember", required = false)MemberVO loginMember, Model model){
        // 로그인 상태 아니라면 메인으로 리다이렉트
        if (loginMember == null)
            return "redirect:/";

        model.addAttribute("loginSession", loginMember);


        return "mypage/setpass";
    }
    @PostMapping("/setpass")
    public String setPass(@SessionAttribute(name = "loginMember", required = false)MemberVO loginMember, Model model,
                          @ModelAttribute PassCheck member, RedirectAttributes redirectAttributes){
        // 로그인 상태 아니라면 메인으로 리다이렉트
        if (loginMember == null)
            return "redirect:/";

        model.addAttribute("loginSession", loginMember);

        boolean result = memberService.updatePass(new MemberVO(loginMember.getEmail(), member.getNewPass()));
        try {
            // 비밀번호 유효성 검사
            if (!loginMember.getPassword().equals(member.getPassword())){ // 기존 비밀번호 불일치
                redirectAttributes.addAttribute("message", "기존 비밀번호와 일치하지 않습니다.");
                return "redirect:/mypage/setpass";
            } else { // 기존은 일치
                if (!member.getNewPass().equals(member.getNewPassCheck())){
                    redirectAttributes.addAttribute("message", "비밀번호 확인에 동일한 값을 입력해주세요.");
                    return "redirect:/mypage/setpass";
                } else if (member.getNewPass().equals(loginMember.getPassword())) {
                        redirectAttributes.addAttribute("message", "기존 비밀번호와 다른 비밀번호를 입력해 주세요.");
                        return "redirect:/mypage/setpass";
                } else if (!result) {
                    redirectAttributes.addAttribute("message", "비밀번호 변경에 실패하였습니다.");
                    return "redirect:/mypage";
                }
            }
        } catch (Exception e) {
            redirectAttributes.addAttribute("message", "시스템에 문제가 발생했습니다");
            return "redirect:/member/passfind";
        }
        redirectAttributes.addAttribute("message",
            "변경된 비밀번호로 로그인 해주시기 바랍니다.");
        return "redirect:/member/login";
    }
/*    @GetMapping("/delete")
    public String delete(@SessionAttribute(name = "loginMember", required = false) MemberVO loginMember, Model model,
                         RedirectAttributes redirectAttributes, HttpSession session) {
        model.addAttribute("loginSession", loginMember);

        System.out.println("delete");
        if (memberService.delete(loginMember.getId()))
            redirectAttributes.addFlashAttribute("result", new ResultDTO(true, "delete"));

        System.out.println("delete");
        session.invalidate();
        return "redirect:/";
    }*/

    @GetMapping("/mypost")
    public String seeMyPost(Criteria criteria, Model model, @SessionAttribute(name = "loginMember", required = false)MemberVO loginMember){
        // 로그인 상태 아니라면 메인으로 리다이렉트
        if (loginMember == null)
            return "redirect:/";

        model.addAttribute("loginSession", loginMember);

        System.out.println("criteria =======" + criteria);
        criteria.setWriter(loginMember.getNickname()); // nickname 가져와서 BoardVO 에 담아 검색 (writer = nickname)
        System.out.println("nickname ---------" + loginMember.getNickname());
        System.out.println("criteria 후 ========= " + criteria);

        long total = myPageService.getTotalPost(criteria);

        if (total != 0){
            model.addAttribute("total", total);
        }
        model.addAttribute("list", myPageService.getMyPost(criteria));
        model.addAttribute("pageMaker", new PageMaker(criteria, total));

        return "mypage/mypost";
    }

    @GetMapping("/mycomment")
    public String seeMyComment(Criteria criteria, Model model, @SessionAttribute(name = "loginMember", required = false)MemberVO loginMember){
        // 로그인 상태 아니라면 메인으로 리다이렉트
        if (loginMember == null)
            return "redirect:/";

        model.addAttribute("loginSession", loginMember);

        criteria.setWriter(loginMember.getNickname());
        List<BoardCommentVO> result = myPageService.getMyComment(criteria);
        System.out.println("result=======" + result);
        long total = myPageService.getTotalComment(criteria);
        model.addAttribute("list", result);
        model.addAttribute("pageMaker", new PageMaker(criteria, total));

        return "mypage/mycomment";
    }
}
