package com.kucw.security.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
public class MyController {

    @GetMapping("/hello")
    public String hello(Authentication authentication) {

        // 取得使用者的帳號
        String username = authentication.getName();

        // 取得使用者的權限
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

        return "Hello " + username + "! 你的權限為: " + authorities;
    }

    @GetMapping("/welcome")
    public String welcome() {
        return "Welcome!";
    }
}
