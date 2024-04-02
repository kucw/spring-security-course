package com.kucw.security.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class MySecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        return http
                // 設定 Session 的創建機制
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.ALWAYS)
                )

                // 設定 CSRF 保護
                .csrf(csrf -> csrf.disable())

                // 添加客製化的 Filter
                .addFilterBefore(new MyFilter(), BasicAuthenticationFilter.class)

                // 設定 Http Basic 認證和表單認證
                .httpBasic(Customizer.withDefaults())
                .formLogin(Customizer.withDefaults())

                // 設定 api 的權限控制
                .authorizeHttpRequests(request -> request
                        // 註冊帳號功能
                        .requestMatchers("/register").permitAll()

                        // 登入功能
                        .requestMatchers("/userLogin").authenticated()

                        // Movie 功能
                        .requestMatchers("/getMovies").hasAnyRole("NORMAL_MEMBER", "MOVIE_MANAGER", "ADMIN")
                        .requestMatchers("/watchFreeMovie").hasAnyRole("NORMAL_MEMBER", "ADMIN")
                        .requestMatchers("/watchVipMovie").hasAnyRole("VIP_MEMBER", "ADMIN")
                        .requestMatchers("/uploadMovie").hasAnyRole("MOVIE_MANAGER", "ADMIN")
                        .requestMatchers("/deleteMovie").hasAnyRole("MOVIE_MANAGER", "ADMIN")

                        // 訂閱和取消訂閱功能
                        .requestMatchers("/subscribe", "/unsubscribe").hasAnyRole("NORMAL_MEMBER", "ADMIN")

                        .anyRequest().denyAll()
                )

                .build();
    }
}
