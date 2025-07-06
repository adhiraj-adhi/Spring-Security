package com.learning.SpringSecurity.dao;

import com.learning.SpringSecurity.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<UserModel, Integer> {
    public UserModel findByUsername(String username);
}
