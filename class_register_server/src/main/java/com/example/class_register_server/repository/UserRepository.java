package com.example.class_register_server.repository;

import com.example.class_register_server.model.User;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository {
    public User findUserByEmail(String email) {
        User user = new User(email, "123");
        user.setFirstName("FirstName");
        user.setLastName("LastName");
        return user;
    }
}
