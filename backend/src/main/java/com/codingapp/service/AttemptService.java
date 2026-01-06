package com.codingapp.service;

import com.codingapp.dto.AttemptDto;
import com.codingapp.mapper.AttemptMapper;
import com.codingapp.model.Attempt;
import com.codingapp.repository.AttemptRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AttemptService {

    private final AttemptRepository attemptRepository;

    public AttemptDto saveAttempt(
            String userId,
            String challengeId,
            String submittedCode,
            Integer score,
            Boolean passed
    ) {
        Attempt attempt = new Attempt();
        attempt.setUserId(userId);
        attempt.setChallengeId(challengeId);
        attempt.setSubmittedCode(submittedCode);
        attempt.setScore(score);
        attempt.setPassed(passed);

        return AttemptMapper.toDto(attemptRepository.save(attempt));
    }

    public List<AttemptDto> getUserAttempts(String userId) {
        return attemptRepository.findByUserId(userId)
                .stream()
                .map(AttemptMapper::toDto)
                .toList();
    }
}
