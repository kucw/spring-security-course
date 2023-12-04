package com.kucw.security.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class MySecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
            .authorizeRequests()
                .mvcMatchers("/welcome", "/register").permitAll()
                .mvcMatchers("/hello").authenticated()
                .anyRequest().denyAll()
            .and().formLogin()
            .and().httpBasic()
            .and().csrf().disable()
            .build();
    }

    // InMemory 創建 User 的用法
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//
//        auth.inMemoryAuthentication()
//                .withUser("test1")
//                .password("{noop}111")
//                .roles("ADMIN", "USER")
//            .and()
//                .withUser("test2")
//                .password("{noop}222")
//                .roles("USER");
//    }
}
