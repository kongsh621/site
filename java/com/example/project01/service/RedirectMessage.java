package com.example.project01.service;

import com.example.project01.domain.Criteria;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Service
public class RedirectMessage {
    public String sendRedirect(RedirectAttributes ra, Long id, Criteria criteria,
                               String message, String redirectUrl){
        ra.addAttribute("id", id);
        ra.addAttribute("message", message);
        if (redirectUrl != null && !redirectUrl.contains("index")) {
            ra.addAttribute("page", criteria.getPage());
            return "redirect:" + redirectUrl;
        }
        // 댓글 페이지는?
        return "redirect:/";
    }
    // id 없는 버전 (로그인) ajax로 위치 읽어와서 페이지 포함
     public String  sendRedirectExceptId(RedirectAttributes ra, String message, String redirectUrl){
        ra.addAttribute("message", message);
        return "redirect:" + redirectUrl;
    }
}
