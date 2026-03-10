package com.example.Passwordchecker.service;

import com.example.Passwordchecker.dto.PasswordResponse;

import java.util.List;

public interface PasswordService {

    /**
     * Analyzes the given password, persists the result, and returns the analysis.
     *
     * @param password the raw password to analyze
     * @return the strength analysis result
     */
    PasswordResponse checkPasswordStrength(String password);

    /**
     * Retrieves the full history of password checks, ordered by most recent first.
     *
     * @return list of past analysis results
     */
    List<PasswordResponse> getPasswordHistory();
}
