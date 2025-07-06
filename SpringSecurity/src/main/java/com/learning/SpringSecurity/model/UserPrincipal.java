package com.learning.SpringSecurity.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

public class UserPrincipal implements UserDetails {
    private UserModel userModel;

    public UserPrincipal(UserModel userModel) {
        this.userModel = userModel;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() { // this is for role
        // since we are not implementing role part now, we will simply return USER role for all.
        return Collections.singleton(new SimpleGrantedAuthority("USER"));
//        Collections.singleton() -> Used to return Collection of single object
    }

    @Override
    public String getPassword() {
        return userModel.getPassword();
    }

    @Override
    public String getUsername() {
        return userModel.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
        // for now we are just returning true but in real-life we have to take care of this as well
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
        // for now we are just returning true but in real-life we have to take care of this as well
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; // for now we are just returning true but in real-life we have to take care of this as well
    }

    @Override
    public boolean isEnabled() {
        return true; // for now we are just returning true but in real-life we have to take care of this as well
    }
}
