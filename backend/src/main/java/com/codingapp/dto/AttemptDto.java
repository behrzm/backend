package com.codingapp.dto;

import java.time.LocalDateTime;

public record AttemptDto(
        String id,
        String userId,
        String challengeId,
        Integer score,
        Boolean passed,
        LocalDateTime submittedAt
) {}
