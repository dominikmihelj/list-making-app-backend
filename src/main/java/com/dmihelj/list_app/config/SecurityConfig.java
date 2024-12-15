package com.dmihelj.list_app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable());

        http.authorizeHttpRequests(auth -> auth
                //.requestMatchers(HttpMethod.GET, "/api/boards", "/api/boards/**", "/api/boards/{boardId}/lists/**", "/api/boards/{boardId}/lists/{listId}/cards/**").permitAll() // Allow access to boards, lists, and cards
//                .requestMatchers(HttpMethod.POST, "/api/**").access(new WebExpressionAuthorizationManager("isAuthenticated()"))
//                .requestMatchers(HttpMethod.PUT, "/api/**").access(new WebExpressionAuthorizationManager("isAuthenticated()"))
//                .requestMatchers(HttpMethod.DELETE, "/api/**").access(new WebExpressionAuthorizationManager("isAuthenticated()"))


                .anyRequest().permitAll()
        );

        http.httpBasic();

        return http.build();
    }
}
