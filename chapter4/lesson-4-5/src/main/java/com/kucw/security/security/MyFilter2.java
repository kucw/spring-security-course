package com.kucw.security.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class MyFilter2 extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        System.out.println("執行 MyFilter2");

        String url = request.getRequestURI();

        if (url.equals("/hello")) {
            System.out.println("path 為 /hello，允許通過");

            // 把 request 和 response 傳下去，交給下一個 Filter 繼續處理
            filterChain.doFilter(request, response);

        } else {
            System.out.println("path 不為 /hello，中斷請求");

            // 設定要返回的 response 給前端
            response.setStatus(500);
        }
    }
}
