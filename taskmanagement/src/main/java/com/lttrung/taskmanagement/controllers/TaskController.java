package com.lttrung.taskmanagement.controllers;

import com.lttrung.taskmanagement.entities.Task;
import com.lttrung.taskmanagement.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TaskController {
    @Autowired
    private TaskService taskService;

    @GetMapping(value = "/api/tasks")
    public ResponseEntity<?> getTasks() {
        try {
            List<Task> tasks = taskService.getTasks();
            return ResponseEntity.ok(tasks);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(null);
        }
    }
}
