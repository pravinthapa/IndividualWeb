package com.system.foodie_hub.controller;

import com.system.foodie_hub.entity.user_management.User;
import com.system.foodie_hub.pojo.user_management.AdminPagePojo;
import com.system.foodie_hub.services.user_management.AdminPageService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;

@Controller
@RequestMapping("th-admin")
@RequiredArgsConstructor
public class ThymeleafAdminPageController {
   private final AdminPageService adminPageService;


    @GetMapping("table")
    public String getDataView(Model model) {
        model.addAttribute("items", adminPageService.getData());
        model.addAttribute("currentuser", getCurrentUser());
        return "table.html";
    }


    @GetMapping("/create")
    public String getFormPage(Model model) {
        model.addAttribute("adminPagePojo", new AdminPagePojo());
        return "booknow.html";
    }

    @PostMapping("/save")
    public String saveData(@Valid AdminPagePojo adminPagePojo) throws IOException {
        adminPageService.saveData(adminPagePojo);
        return "redirect:/th-admin/table";
    }

    public String getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentemail = authentication.getName();
        return currentemail;
    }

//    public User getUser(String email){
//        User u = userService.fatchByEmail(email);
//        return u;
//    }

}
