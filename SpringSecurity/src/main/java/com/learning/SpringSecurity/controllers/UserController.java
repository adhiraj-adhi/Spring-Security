package com.learning.SpringSecurity.controllers;

import com.learning.SpringSecurity.model.UserModel;
import com.learning.SpringSecurity.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    private UserService userService;
    @PostMapping("/register")
    public UserModel registerController(@RequestBody UserModel user) {
        return userService.registerService(user);
    }
}
