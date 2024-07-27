package com.kucw.security.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {

    @GetMapping("/order")
    public String order(@AuthenticationPrincipal Jwt jwt) {

        jwt.getIssuedAt();

        return "返回訂單數據";
    }
}
