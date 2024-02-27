package com.kucw.security.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WelcomeController {

    @RequestMapping("/welcome")
    public String welcome() {
        System.out.println("執行 /welcome");
        return "Welcome!";
    }
}
