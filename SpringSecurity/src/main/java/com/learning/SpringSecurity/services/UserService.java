package com.learning.SpringSecurity.services;

import com.learning.SpringSecurity.dao.UserRepo;
import com.learning.SpringSecurity.model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepo userRepo;

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12); // 12 = rounds
    public UserModel registerService(UserModel user) {
        // before saving the details to database let us Encrypt the password:
        user.setPassword(encoder.encode(user.getPassword()));  // encoding
        return userRepo.save(user);
    }
}
