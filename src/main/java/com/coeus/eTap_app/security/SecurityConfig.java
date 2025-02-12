package com.coeus.eTap_app.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeHttpRequests()
                .requestMatchers(
                        "/auth/register",
                        "/auth/login",
                        // Swagger UI üçün endpointlər
                        "/swagger-ui.html",
                        "/swagger-ui/**",          // Bütün Swagger UI faylları
                        "/v3/api-docs",            // OpenAPI spesifikasiyası
                        "/v3/api-docs/**",         // OpenAPI alt endpointləri
                        "/swagger-resources/**",   // Swagger resursları
                        "/webjars/**",             // JavaScript/CSS faylları
                        "/swagger-ui/index.html",  // Springdoc üçün əsas səhifə
                        "/favicon.ico"             // Favicon (əgər lazımdırsa)
                ).permitAll()
                .anyRequest().authenticated()
                .and()
                .httpBasic().disable()
                .formLogin().disable();

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
