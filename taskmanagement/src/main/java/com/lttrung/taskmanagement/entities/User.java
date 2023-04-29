package com.lttrung.taskmanagement.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.util.List;

@Data
@Entity(name = "users")
public class User {
    @Id
    private String username;
    @JsonIgnore
    private String password;
    private String fullName;
    private Integer role;
    @JsonBackReference
    @OneToMany(mappedBy = "user")
    private List<Task> tasks;
}
