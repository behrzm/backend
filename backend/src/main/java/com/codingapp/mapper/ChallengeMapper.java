package com.codingapp.mapper;

import com.codingapp.dto.ChallengeDto;
import com.codingapp.model.Challenge;

public class ChallengeMapper {

    public static ChallengeDto toDto(Challenge c) {
        return new ChallengeDto(
                c.getId().toString(),
                c.getTitle(),
                c.getDescription(),
                c.getDifficulty().name(),
                c.getCategory(),
                c.getBasePoints(),
                c.getStarterCode(),
                c.getIsActive()
        );
    }
}
