package com.learning.SpringSecurity.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
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
//                .authorizeHttpRequests(request -> request.anyRequest().authenticated())
                .authorizeHttpRequests(request -> request
                        .requestMatchers("/db-login").permitAll()  // allow public access
                        .anyRequest().authenticated()
                )
                .httpBasic(Customizer.withDefaults())
                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        return http.build(); // returns SecurityFilterChain
    }

    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails user1 = User
                .withDefaultPasswordEncoder()  // for time-being we are using it but we know that password are encoded
                .username("adhiraj")
                .password("a@123")
                .roles("USER")
                .build();

        UserDetails user2 = User
                .withDefaultPasswordEncoder() // this is also deprecated
                .username("admin")
                .password("admin")
                .roles("ADMIN")
                .build();
        /*
        * UserDetailsService is an interface so we will need to have a custom implementation
        * class created for it or we can use an inbuilt implementation class say like:
        * InMemoryUserDetailsManager which implements UserDetailsManager and UserDetailsManager
        * in turn implements UserDetailsService.
        * InMemoryUserDetailsManager has an interface with var args UserDetails, we will
        * use that here.
        * */
        return new InMemoryUserDetailsManager(user1, user2);
    }
}
