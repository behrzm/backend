package com.codingapp.controller;

import com.codingapp.dto.ChallengeDto;
import com.codingapp.service.ChallengeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/challenges")
@RequiredArgsConstructor
@CrossOrigin
public class ChallengeController {

    private final ChallengeService challengeService;

    @GetMapping
    public List<ChallengeDto> getAll() {
        return challengeService.getAll();
    }
}
