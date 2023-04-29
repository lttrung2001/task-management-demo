package com.lttrung.taskmanagement.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Response<T> {
    @JsonProperty("message")
    private String message;
    @JsonProperty("data")
    private T data;
}
