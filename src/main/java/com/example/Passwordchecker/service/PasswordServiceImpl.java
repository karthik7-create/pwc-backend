package com.example.Passwordchecker.service;

import com.example.Passwordchecker.dto.PasswordResponse;
import com.example.Passwordchecker.entity.PasswordHistory;
import com.example.Passwordchecker.repository.PasswordHistoryRepository;
import com.example.Passwordchecker.util.PasswordStrengthUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class PasswordServiceImpl implements PasswordService {

    private final PasswordStrengthUtil passwordStrengthUtil;
    private final PasswordHistoryRepository passwordHistoryRepository;

    @Override
    @Transactional
    public PasswordResponse checkPasswordStrength(String password) {
        log.info("Analyzing password strength (length: {})", password.length());

        // 1. Calculate score, strength, and suggestions
        int score = passwordStrengthUtil.calculateScore(password);
        String strength = passwordStrengthUtil.determineStrength(score);
        List<String> suggestions = passwordStrengthUtil.generateSuggestions(password);

        // 2. Persist the analysis result (never store the raw password)
        PasswordHistory history = PasswordHistory.builder()
                .passwordLength(password.length())
                .strength(strength)
                .score(score)
                .suggestions(String.join("; ", suggestions))
                .build();

        PasswordHistory saved = passwordHistoryRepository.save(history);
        log.info("Password analysis saved with id={}, strength={}, score={}", saved.getId(), strength, score);

        // 3. Build and return the response DTO
        return PasswordResponse.builder()
                .strength(strength)
                .score(score)
                .passwordLength(password.length())
                .suggestions(suggestions)
                .createdAt(saved.getCreatedAt())
                .build();
    }

    @Override
    @Transactional(readOnly = true)
    public List<PasswordResponse> getPasswordHistory() {
        log.info("Fetching password check history");

        return passwordHistoryRepository.findAllByOrderByCreatedAtDesc()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    // ─── Private Helpers ──────────────────────────────────────────────

    private PasswordResponse mapToResponse(PasswordHistory entity) {
        return PasswordResponse.builder()
                .strength(entity.getStrength())
                .score(entity.getScore())
                .passwordLength(entity.getPasswordLength())
                .createdAt(entity.getCreatedAt())
                .build();
    }
}
