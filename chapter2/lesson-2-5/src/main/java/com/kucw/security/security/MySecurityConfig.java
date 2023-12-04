package com.kucw.security.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class MySecurityConfig {



//    @Bean
//    public InMemoryUserDetailsManager userDetailsManager() {
//        UserDetails userTest1 = User
//                .withUsername("test1")
//                .password("{noop}111")
//                .roles("ADMIN", "USER")
//                .build();
//
//        UserDetails userTest2 = User
//                .withUsername("test2")
//                .password("{bcrypt}$2a$12$iiZJ4Y7NpSlLf1slp5jOwOaNODGf0Dsb2kAMBw.MBwA8Q27.2Vnka")
//                .roles("USER")
//                .build();
//
//        return new InMemoryUserDetailsManager(userTest1, userTest2);
//    }
}
