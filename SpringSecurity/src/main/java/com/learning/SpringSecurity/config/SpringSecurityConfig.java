package com.learning.SpringSecurity.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
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

    // creating our own AuthenticationProvider
    @Autowired
    private UserDetailsService userDetailsService;
    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(new BCryptPasswordEncoder(12));
        /* changing the password encoder to Bcrypt because now user's entered password
            (plain text) will be encoded and checked with the database password. Here, 12
            is the round
         */
        provider.setUserDetailsService(userDetailsService); // provider using userDetailsService
        return provider;
    }
}
