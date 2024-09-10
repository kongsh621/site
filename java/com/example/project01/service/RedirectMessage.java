package com.example.project01.service;

import com.example.project01.domain.Criteria;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Service
public class RedirectMessage {
    public void sendRedirect(RedirectAttributes ra, Long id, Criteria criteria,
                             String message, Model model, String name, Object object){
        ra.addAttribute("id", id);
        ra.addAttribute("page", criteria.getPage());
        ra.addAttribute("message", message);
        model.addAttribute(name, object);
    }
    // id 없는 버전 (로그인)
    public void sendRedirectExceptId(RedirectAttributes ra, Criteria criteria,
                             String message, Model model, String name, Object object){
        ra.addAttribute("page", criteria.getPage());
        ra.addAttribute("message", message);
        model.addAttribute(name, object);
    }
}
