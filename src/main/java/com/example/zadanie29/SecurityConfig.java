package com.example.zadanie29;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        PathRequest.H2ConsoleRequestMatcher h2ConsoleRequestMatcher = PathRequest.toH2Console();
        http.authorizeHttpRequests(request -> request
                .requestMatchers("/usersList/**").hasRole("Admin")
                .requestMatchers("/changeRole/**").hasRole("Admin")
                .requestMatchers("/resources/**").permitAll()
                .requestMatchers("/register").permitAll()
                .requestMatchers(h2ConsoleRequestMatcher).permitAll()
                .requestMatchers("/").permitAll()
                .anyRequest().authenticated()
        );
        http.formLogin(form -> form.loginPage("/login").successForwardUrl("/").permitAll());
        http.logout(logout -> logout.logoutSuccessUrl("/").permitAll());
        http.csrf().ignoringRequestMatchers("/login").disable();
        http.headers().frameOptions().sameOrigin();
        return http.build();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
}
