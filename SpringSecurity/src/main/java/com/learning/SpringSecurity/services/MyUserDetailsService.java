package com.learning.SpringSecurity.services;

import com.learning.SpringSecurity.dao.UserRepo;
import com.learning.SpringSecurity.model.UserModel;
import com.learning.SpringSecurity.model.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("Looking up user: " + username);
        UserModel user = userRepo.findByUsername(username);
        if (user==null) {
            System.out.println("User not found");
            throw new UsernameNotFoundException("user not found");
        }

        /*
        * The return type of this method is UserDetails which is an interface so we will need
        * to have some inbuilt implementation class or our custom implementation class.
        * We will create our own custom implementation class named UserPrincipal.
        * */

        return new UserPrincipal(user);
    }
}
