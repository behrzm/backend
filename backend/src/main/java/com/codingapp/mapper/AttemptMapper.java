package com.codingapp.mapper;

import com.codingapp.dto.AttemptDto;
import com.codingapp.model.Attempt;

public class AttemptMapper {

    public static AttemptDto toDto(Attempt a) {
        return new AttemptDto(
                a.getId().toString(),
                a.getUserId().toString(),
                a.getChallengeId().toString(),
                a.getScore(),
                a.getPassed(),
                a.getSubmittedAt()
        );
    }

}
