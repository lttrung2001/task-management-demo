package com.lttrung.taskmanagement.services.impl;

import com.lttrung.taskmanagement.entities.User;
import com.lttrung.taskmanagement.repositories.UserRepository;
import com.lttrung.taskmanagement.services.UserService;
import com.lttrung.taskmanagement.utils.JwtHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    JwtHelper jwtHelper;
    @Autowired
    UserRepository userRepository;

    @Override
    public String login(String username, String password) {
        Optional<User> userContainer = userRepository.findById(username);
        if (userContainer.isEmpty()) {
            throw new RuntimeException("User does not exist!");
        }
        User user = userContainer.get();
        if (!user.getPassword().equals(password)) {
            throw new RuntimeException("Wrong password!");
        }
        return jwtHelper.generateToken(user);
    }

    @Override
    public void register(String username, String password, String fullName) {

    }

    @Override
    public void changePassword(String username, String oldPassword, String newPassword) {
        Optional<User> optionalUser = userRepository.findById(username);
        if (optionalUser.isEmpty()) {
            throw new RuntimeException("User does not exist!");
        }
        User user = optionalUser.get();
        if (!user.getPassword().equals(oldPassword)) {
            throw new RuntimeException("Current password incorrect!");
        }
        user.setPassword(newPassword);
        userRepository.save(user);
    }
}
