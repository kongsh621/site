package com.example.project01.service;

import com.example.project01.domain.Criteria;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Service
public class RedirectMessage {
    public String sendRedirect(RedirectAttributes ra,
                               @RequestParam(name="id", required = false)Long id, Criteria criteria,
                               String message, String redirectUrl){
        // id만 필요없을 때(인포보드에서 찜할 때)
        if (id != null)
            ra.addAttribute("id", id);

        ra.addAttribute("message", message);

        if (criteria == null){ // 페이지 필요 없을 때
            return "redirect:" + redirectUrl;
        }
        if (redirectUrl != null && !redirectUrl.contains("index")) {
            ra.addAttribute("page", criteria.getPage());
            return "redirect:" + redirectUrl;
        }
        return "redirect:/";
    }
    // id, page 둘 다 없는 버전 (로그인) ajax로 위치 읽어와서 페이지 포함 / 검색
     public String  sendRedirectExceptId(RedirectAttributes ra, String message, String redirectUrl){
        ra.addAttribute("message", message);
        return "redirect:" + redirectUrl;
    }
}
