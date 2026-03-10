package com.example.Passwordchecker.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PasswordResponse {

    private String strength;
    private int score;
    private int passwordLength;
    private List<String> suggestions;
    private LocalDateTime createdAt;
}
