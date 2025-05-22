package com.example.bilabonnement.Controller;

import com.example.bilabonnement.Model.loginModel;
import com.example.bilabonnement.Service.loginService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class loginController {

    private final loginService loginService;

    public loginController(loginService loginService) {
        this.loginService = loginService;
    }

    @GetMapping("/login")
    public String visLoginSide() {
        return "login";
    }

}
