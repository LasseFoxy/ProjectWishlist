package com.example.projectwishlist.service;

import com.example.projectwishlist.model.User;
import com.example.projectwishlist.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    //Forbinder controller og repository (se repository for yderligere forklaringer)
    public void save(User user) {
        userRepository.save(user);
    }

    // Tjekker om en brugers login-oplysninger er korrekte
    public User validateUser(String usernameOrEmail, String password) {
        User user = userRepository.findByUsernameOrEmail(usernameOrEmail);
        if (user != null && user.getPassword().equals(password)) {
            return user;
        } else {
            return null;
        }
    }
    public User getUserById(int userId) {
        return userRepository.findById(userId);
    }

    public void updateUser(User user) {
        userRepository.update(user);
    }
}


