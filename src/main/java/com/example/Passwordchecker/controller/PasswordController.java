package com.example.Passwordchecker.controller;

import com.example.Passwordchecker.dto.PasswordRequest;
import com.example.Passwordchecker.dto.PasswordResponse;
import com.example.Passwordchecker.service.PasswordService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/password")
@RequiredArgsConstructor
public class PasswordController {

    private final PasswordService passwordService;

    /**
     * POST /api/password/check
     * Analyzes the submitted password and returns the strength result.
     */
    @PostMapping("/check")
    public ResponseEntity<PasswordResponse> checkPassword(
            @Valid @RequestBody PasswordRequest request) {

        PasswordResponse response = passwordService.checkPasswordStrength(request.getPassword());
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    /**
     * GET /api/password/history
     * Returns a list of all previous password strength analyses.
     */
    @GetMapping("/history")
    public ResponseEntity<List<PasswordResponse>> getHistory() {
        List<PasswordResponse> history = passwordService.getPasswordHistory();
        return ResponseEntity.ok(history);
    }
}
