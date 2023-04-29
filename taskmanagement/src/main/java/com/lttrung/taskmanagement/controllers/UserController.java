package com.lttrung.taskmanagement.controllers;

import com.lttrung.taskmanagement.models.JwtResponse;
import com.lttrung.taskmanagement.models.Response;
import com.lttrung.taskmanagement.services.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping(value = "/api/login", consumes = {MediaType.ALL_VALUE})
    public ResponseEntity<?> login(@RequestParam("username") String username,
                                   @RequestParam("password") String password) {
        try {
            String accessToken = userService.login(username, password);
            return ResponseEntity.ok(new JwtResponse("Bearer", accessToken));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new Response<String>("Wrong email, password!", null));
        }
    }

    @PreAuthorize("USER")
    @PatchMapping(value = "/api/change-password", consumes = {MediaType.ALL_VALUE})
    public ResponseEntity<?> changePassword(HttpServletRequest request,
                                            @RequestParam("oldPassword") String oldPassword,
                                            @RequestParam("newPassword") String newPassword) {
        try {
            String username = (String) request.getAttribute("username");
            userService.changePassword(username, oldPassword, newPassword);
            return ResponseEntity.ok(new Response<String>("Change password successfully!", null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new Response<String>("Change password failed!", null));
        }
    }
}
