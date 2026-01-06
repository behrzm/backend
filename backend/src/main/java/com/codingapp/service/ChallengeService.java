package com.codingapp.service;

import com.codingapp.dto.ChallengeDto;
import com.codingapp.mapper.ChallengeMapper;
import com.codingapp.repository.ChallengeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ChallengeService {

    private final ChallengeRepository challengeRepository;

    public List<ChallengeDto> getAll() {
        return challengeRepository.findAll()
                .stream()
                .map(ChallengeMapper::toDto)
                .toList();
    }
}
