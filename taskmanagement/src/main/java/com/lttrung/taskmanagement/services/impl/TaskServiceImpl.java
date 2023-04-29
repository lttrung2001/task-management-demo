package com.lttrung.taskmanagement.services.impl;

import com.lttrung.taskmanagement.entities.Task;
import com.lttrung.taskmanagement.repositories.TaskRepository;
import com.lttrung.taskmanagement.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {
    @Autowired
    private TaskRepository taskRepository;
    @Override
    public List<Task> getTasks() {
        return taskRepository.findAll();
    }
}
