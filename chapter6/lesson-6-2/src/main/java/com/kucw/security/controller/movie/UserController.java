package com.kucw.security.controller.movie;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @PostMapping("/register")
    public String register() {
        return "註冊新帳號";
    }

    @PostMapping("/userLogin")
    public String userLogin() {
        return "登入";
    }
}