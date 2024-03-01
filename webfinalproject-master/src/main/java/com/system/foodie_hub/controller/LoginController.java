package com.system.foodie_hub.controller;


import com.system.foodie_hub.pojo.user_management.UserPojo;
import com.system.foodie_hub.services.user_management.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;

@Controller
@RequestMapping
@RequiredArgsConstructor
public class LoginController {
    private final UserService userService;



    @GetMapping("/register")
    public String getRegister(Model model) {
        System.out.println("Request hit at register");
        model.addAttribute("user", new UserPojo());
        return "register";
    }

    @GetMapping("/login")
    public String getpage(Model model) {
        model.addAttribute("user", new UserPojo());
        return "login";
    }


    @PostMapping("/logout")
    public  String logoutMethod(Authentication authentication){
        if(authentication.isAuthenticated()){
            SecurityContextHolder.clearContext();
        }
        return "redirect:/login";
    }

    @PostMapping("/createUser")
    public String createUser(@ModelAttribute UserPojo userPojo) throws IOException {
        System.out.println(userPojo);
        userService.save(userPojo);
        return "/login";
    }
}