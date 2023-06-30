package com.example.demo.controller;

import com.example.demo.service.Oauth2Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SecurityController {
    @Autowired
    Oauth2Service oauth2Service;
    @RequestMapping("/security/login/form")
    public String loginForm(Model model) {
        model.addAttribute("message", "Đăng Nhập Để Nhận Nhiều Ưu Đãi");
        return "security/login";
    }

    @RequestMapping("/security/login/success")
    public String success(Model model) {
        model.addAttribute("message", "Dang Nhap thanh cong");
        return "redirect:/product/list";
    }

    @RequestMapping("/security/unauthoríed")
    public String unauthoríed(Model model) {
        model.addAttribute("message", "Bạn Không Có Quyền Vào Đây");
        return "security/login";
    }
    @RequestMapping("/oauth2/login/success")
    public String oauth2(OAuth2AuthenticationToken oauth2){
        oauth2Service.loginFromOAuth2(oauth2);
        return "redirect:/product/list";
    }
}
