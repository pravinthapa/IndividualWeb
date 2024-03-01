package com.system.foodie_hub.controller;

import com.system.foodie_hub.pojo.user_management.HomePojo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Base64;

@Controller
@RequestMapping("th-home")
@RequiredArgsConstructor
public class ThymeleafHomeController {
//    private final AdminPageService adminPageService;
    @GetMapping("create")
    public String getFormPage(Model model){
        model.addAttribute("home", new HomePojo());
        return "dashboard";
    }

    public String getImageBase64(String fileName) {
        String filePath = System.getProperty("user.dir") + "/file_server/";
        File file = new File(filePath + fileName);
        byte[] bytes = new byte[0];
        try {
            bytes = Files.readAllBytes(file.toPath());
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        String base64 = Base64.getEncoder().encodeToString(bytes);
        return base64;
    }
}