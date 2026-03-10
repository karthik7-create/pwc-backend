package com.example.Passwordchecker.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "password_history")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PasswordHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "password_length", nullable = false)
    private int passwordLength;

    @Column(name = "strength", nullable = false, length = 10)
    private String strength;

    @Column(name = "score", nullable = false)
    private int score;

    @Column(name = "suggestions", columnDefinition = "TEXT")
    private String suggestions;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;
}
