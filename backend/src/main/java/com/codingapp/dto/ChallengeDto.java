package com.codingapp.dto;


public record ChallengeDto(
        String id,
        String title,
        String description,
        String difficulty,
        String category,
        Integer basePoints,
        String starterCode,
        Boolean isActive
) {}

