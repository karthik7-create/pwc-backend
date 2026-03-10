package com.example.Passwordchecker.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PasswordRequest {

    @NotBlank(message = "Password must not be blank")
    @Size(min = 4, message = "Password must be at least 4 characters long")
    private String password;
}
