package com.learning.SpringSecurity.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity  // this basically means use this class as the security settings and the parts we customize here are overridden — everything else remains as default.
public class SpringSecurityConfig {
/*
* Just adding @EnableWebSecurity doesn't change anything by itself. We must also provide a
* SecurityFilterChain bean (in Spring Security 6+) or extend WebSecurityConfigurerAdapter
* (deprecated in Spring Security 6) to customize the behavior.
* */

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(customizer -> customizer.disable())
                .authorizeHttpRequests(request -> request.anyRequest().authenticated())
                .httpBasic(Customizer.withDefaults())
                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        return http.build(); // returns SecurityFilterChain
    }
}
