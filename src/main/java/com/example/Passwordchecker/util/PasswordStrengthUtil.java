package com.example.Passwordchecker.util;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Utility class responsible for analyzing password strength.
 * <p>
 * Scoring rules:
 * <ul>
 *   <li>Length ≥ 8  → +2</li>
 *   <li>Length ≥ 12 → +2 (additional)</li>
 *   <li>Contains uppercase → +1</li>
 *   <li>Contains lowercase → +1</li>
 *   <li>Contains digit → +1</li>
 *   <li>Contains special character → +2</li>
 *   <li>No repeated characters → +1</li>
 * </ul>
 * Maximum possible score: 10
 */
@Component
public class PasswordStrengthUtil {

    private static final String SPECIAL_CHARACTERS = "!@#$%^&*()_+-=[]{}|;':\",./<>?`~";

    // ─── Individual Rule Checks ───────────────────────────────────────

    public boolean containsUpperCase(String password) {
        return password.chars().anyMatch(Character::isUpperCase);
    }

    public boolean containsLowerCase(String password) {
        return password.chars().anyMatch(Character::isLowerCase);
    }

    public boolean containsNumber(String password) {
        return password.chars().anyMatch(Character::isDigit);
    }

    public boolean containsSpecialCharacter(String password) {
        return password.chars().anyMatch(ch -> SPECIAL_CHARACTERS.indexOf(ch) >= 0);
    }

    public boolean hasNoRepeatedCharacters(String password) {
        Set<Character> seen = new HashSet<>();
        for (char c : password.toCharArray()) {
            if (!seen.add(c)) {
                return false;
            }
        }
        return true;
    }

    // ─── Score Calculation ────────────────────────────────────────────

    public int calculateScore(String password) {
        int score = 0;

        if (password.length() >= 8)  score += 2;
        if (password.length() >= 12) score += 2;
        if (containsUpperCase(password))        score += 1;
        if (containsLowerCase(password))        score += 1;
        if (containsNumber(password))           score += 1;
        if (containsSpecialCharacter(password)) score += 2;
        if (hasNoRepeatedCharacters(password))  score += 1;

        return Math.min(score, 10); // cap at 10
    }

    // ─── Strength Classification ─────────────────────────────────────

    public String determineStrength(int score) {
        if (score >= 7) return "STRONG";
        if (score >= 4) return "MEDIUM";
        return "WEAK";
    }

    // ─── Suggestion Generation ───────────────────────────────────────

    public List<String> generateSuggestions(String password) {
        List<String> suggestions = new ArrayList<>();

        if (password.length() < 8) {
            suggestions.add("Increase password length to at least 8 characters");
        } else if (password.length() < 12) {
            suggestions.add("Increase password length to at least 12 characters for better security");
        }

        if (!containsUpperCase(password)) {
            suggestions.add("Add uppercase letters (A-Z)");
        }

        if (!containsLowerCase(password)) {
            suggestions.add("Add lowercase letters (a-z)");
        }

        if (!containsNumber(password)) {
            suggestions.add("Add numbers (0-9)");
        }

        if (!containsSpecialCharacter(password)) {
            suggestions.add("Add special characters (e.g., @, #, $, %, &)");
        }

        if (!hasNoRepeatedCharacters(password)) {
            suggestions.add("Avoid repeated characters");
        }

        return suggestions;
    }
}
