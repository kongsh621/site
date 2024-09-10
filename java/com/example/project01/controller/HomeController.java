package com.example.project01.controller;

import com.example.project01.domain.*;
import com.example.project01.service.InfoBoardService;
import com.example.project01.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.HashMap;
import java.util.List;
import java.util.Set;

@Controller
public class HomeController {

    @Autowired
    private UserService userService;

    @Autowired
    private InfoBoardService infoBoardService;

    @GetMapping({"/", "/index"})
    public String index(Model model, MemberVO memberVO, @SessionAttribute(name = "loginMember", required= false) MemberVO loginMember,
                        @SessionAttribute(name = "favorites", required = false) Set<JjimVO> favorites, Criteria criteria,
                        HttpServletRequest request){
        System.out.println("index");
        model.addAttribute("loginSession", loginMember);
        model.addAttribute("loginMember", loginMember);
        model.addAttribute("favorites", favorites);
        // 세션에 현재 위치 저장 -> 찜 삭제 했을 때 다시 돌아오기 위함
        HttpSession session = request.getSession();
        session.setAttribute("previousPage", request.getRequestURI());

        List<InfoBoardVO> list = infoBoardService.getRecommendList();

        System.out.println("Main List " + list);
        model.addAttribute("list", list);
        model.addAttribute("pageMaker", new PageMaker(criteria, 8)); // 8개 출력

        return "mainview";
    }

    @GetMapping("/logout")
    public String logout (HttpServletRequest request, Model model, RedirectAttributes redirectAttributes) {
        HttpSession session = request.getSession(false); // 세션 값 받고 (기존 세션 반환, 없으면 null 반환)
            // 세션 전체 무효화
            session.invalidate();
            redirectAttributes.addAttribute("message", "로그아웃 되었습니다");
            System.out.println("로그아웃 되었습니다.");
        return "redirect:/index";
    }

}
