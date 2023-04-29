package com.lttrung.taskmanagement.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class JwtResponse {
    @JsonProperty(value = "token_type")
    private String tokenType;
    @JsonProperty(value = "token")
    private String token;
}
