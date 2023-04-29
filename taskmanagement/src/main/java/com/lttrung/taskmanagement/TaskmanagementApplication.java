package com.lttrung.taskmanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(scanBasePackages = "com.lttrung.taskmanagement"
        , exclude = SecurityAutoConfiguration.class
)
public class TaskmanagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(TaskmanagementApplication.class, args);
    }

}
