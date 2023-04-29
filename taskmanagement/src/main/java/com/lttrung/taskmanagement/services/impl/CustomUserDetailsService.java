package com.lttrung.taskmanagement.services.impl;

import com.lttrung.taskmanagement.entities.User;
import com.lttrung.taskmanagement.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CustomUserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> optionalUser =  userRepository.findById(username);
        if (optionalUser.isEmpty()) {
            throw new RuntimeException("User not found!");
        }
        User user = optionalUser.get();
        List<GrantedAuthority> roles = new ArrayList<>();
        roles.add((GrantedAuthority) () -> {
            String role = "USER";
            if (user.getRole() == 1) {
                role = "ADMIN";
            }
            return String.valueOf(new SimpleGrantedAuthority(role));
        });
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), roles);
    }
}
