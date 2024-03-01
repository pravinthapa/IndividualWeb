package com.system.foodie_hub.controller;

import com.system.foodie_hub.pojo.user_management.AdminPagePojo;
import com.system.foodie_hub.services.user_management.AdminPageService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;

@Controller
@RequestMapping
@RequiredArgsConstructor
public class PricingController {
    private final AdminPageService adminPageService;
    @GetMapping("/pricing")
    public String getPricingPage(){
        return "pricing.html";
    }


//    @GetMapping("/book-now")
//    public String getBookPage(){
//        return "booknow.html";
//    }

    @GetMapping("/create")
    public String getFormPage(Model model) {
        model.addAttribute("adminPagePojo", new AdminPagePojo());
        return "booknow.html";
    }

    @PostMapping("/save")
    public String saveData(@Valid AdminPagePojo adminPagePojo) throws IOException {
        adminPageService.saveData(adminPagePojo);
        return "redirect:/pricing";
    }
}


