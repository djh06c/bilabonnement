package com.example.bilabonnement.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class homeController {

    // ➤ Standard forsiden (index.html)
    @GetMapping("/index")
    public String mainMenu() {
        return "index";
    }

    // ➤ Elegant redirect: localhost:8080/ → /index
    @GetMapping("/")
    public String redirectToIndex() {
        return "redirect:/index";
    }
}
