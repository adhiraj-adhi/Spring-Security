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
        http.csrf(customizer -> customizer.disable());  // to disable csrf we can have this lambda
        /*
        * But, still all the requests are not authenticated as anyone can access any endpoint.
        * To authenticate every request we can use the following lambda:
        *  */
        http.authorizeHttpRequests(request -> request.anyRequest().authenticated());

        /**
         * But now if we try to access any endpoint we will get the "Access to localhost was
         * denied" error. This is so because authentication is applied. But how to authenticate
         * ourself as we do not have form or anything as such to have ourself authenticated.
         * To enable form-login we can do:
         */
        // http.formLogin(Customizer.withDefaults()); // form-login with default settings
        // We are commenting last line because we are making our session stateless.
        /*
        * The above form-login works as earlier with session and everything but if we try to
        * hit say "localhost:8080" using POSTMAN than we will get the 200 status with HTML form
        * as response which is nothing but authentication form that we enabled above. To tackle
        * this problem we can do the following:
        * */
        http.httpBasic(Customizer.withDefaults());

        http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        // see the below for note on above line.

        return http.build(); // returns SecurityFilterChain
    }
}
