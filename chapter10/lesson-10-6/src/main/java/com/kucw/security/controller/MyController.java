package com.kucw.security.controller;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyController {

    @GetMapping("/")
    public String index(Authentication authentication) {

        // 取得使用者的帳號（若使用 OAuth2 登入，會使用 providerId 的值當作帳號）
        String username = authentication.getName();

        return "Hello " + username;
    }

    @GetMapping("/welcome")
    public String welcome() {
        return "Welcome!";
    }
}
