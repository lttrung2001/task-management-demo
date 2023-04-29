package com.lttrung.taskmanagement.services;

public interface UserService {
    String login(String username, String password);

    void register(String username, String password, String fullName);

    void changePassword(String username, String oldPassword, String newPassword);
}
