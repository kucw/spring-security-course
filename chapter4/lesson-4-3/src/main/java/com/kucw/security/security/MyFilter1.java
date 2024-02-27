package com.kucw.security.security;

import jakarta.servlet.*;

import java.io.IOException;

public class MyFilter1 implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("執行 MyFilter1");

        // 把 request 和 response 傳下去，交給下一個 Filter 繼續處理
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
