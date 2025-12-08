package com.codingapp.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import java.time.LocalDateTime;

@Entity
@Table(name = "attempts")
@Data
@NoArgsConstructor
public class Attempt {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    @Column(name = "user_id", nullable = false)
    private String userId;

    @Column(name = "challenge_id", nullable = false)
    private String challengeId;

    @Column(name = "submitted_code", nullable = false, columnDefinition = "TEXT")
    private String submittedCode;

    @Column(nullable = false)
    private Integer score = 0;

    @Column(nullable = false)
    private Boolean passed = false;

    @Column(name = "submitted_at")
    private LocalDateTime submittedAt = LocalDateTime.now();
}