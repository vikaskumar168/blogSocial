package com.vikas.social.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // REQUIRED for Post/Put/Delete to work
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/blogSocial/**").permitAll() // Allows ALL methods on this path
                        .anyRequest().authenticated() // Everything else requires login
                );

        return http.build();
    }
}