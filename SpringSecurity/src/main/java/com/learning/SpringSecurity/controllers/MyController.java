package com.learning.SpringSecurity.controllers;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class MyController {
    @GetMapping("/")
    public String greet(HttpServletRequest request) {
        return "Hello Adhiraj & token: "+request.getSession().getId();
    }
}
