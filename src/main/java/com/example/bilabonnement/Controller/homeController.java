package com.example.bilabonnement.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class homeController {

    // forsiden
    @GetMapping("/index")
    public String mainMenu() {
        return "index";
    }


    //redirect /index
    @GetMapping("/")
    public String redirectToIndex() {
        return "redirect:/index";
    }
}
