package com.example.Passwordchecker.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Map;

@RestController
public class HomeController {

    @GetMapping("/")
    public ResponseEntity<Map<String, Object>> home() {
        return ResponseEntity.ok(Map.of(
                "service", "Password Strength Checker API",
                "status", "UP",
                "timestamp", LocalDateTime.now().toString(),
                "endpoints", Map.of(
                        "checkPassword", "POST /api/password/check",
                        "getHistory", "GET /api/password/history"
                )
        ));
    }
}
