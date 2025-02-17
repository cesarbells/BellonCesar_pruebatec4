package com.agencia.agencia.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
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
        http
                .csrf().disable()
                .authorizeHttpRequests()
                // Permitir acceso público a Swagger
                .requestMatchers("/swagger-ui/**", "/v3/api-docs/**", "/swagger-resources/**", "/webjars/**").permitAll()
                // Permitir GET sin autenticación
                .requestMatchers(HttpMethod.GET, "/agency/hotels/**", "/agency/flights/**", "/agency/room-bookings/**").permitAll()
                // Requerir autenticación para POST, PUT y DELETE
                .requestMatchers(HttpMethod.POST, "/agency/**").authenticated()
                .requestMatchers(HttpMethod.PUT, "/agency/**").authenticated()
                .requestMatchers(HttpMethod.DELETE, "/agency/**").authenticated()
                .anyRequest().permitAll()
                .and()
                .httpBasic(); // Habilitar autenticación básica

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}


