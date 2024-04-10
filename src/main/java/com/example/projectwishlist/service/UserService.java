package com.example.projectwishlist.service;

import com.example.projectwishlist.model.User;
import com.example.projectwishlist.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public void save(User user) {
        userRepository.save(user);
    }

    // UserService
    public User validateUser(String usernameOrEmail, String password) {
        User user = userRepository.findByUsernameOrEmail(usernameOrEmail);
        if (user != null && user.getPassword().equals(password)) {
            return user;
        } else {
            return null;
        }
    }
    public User getUserById(Long userId) {
        return userRepository.findById(userId);
    }

    public void updateUser(User user) {
        userRepository.update(user);
    }
}


