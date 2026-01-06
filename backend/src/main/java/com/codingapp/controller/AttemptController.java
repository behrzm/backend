package com.codingapp.controller;

import com.codingapp.dto.AttemptDto;
import com.codingapp.service.AttemptService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/attempts")
@RequiredArgsConstructor
@CrossOrigin
public class AttemptController {

    private final AttemptService attemptService;

    @PostMapping
    public AttemptDto submitAttempt(
            @RequestParam String userId,
            @RequestParam String challengeId,
            @RequestParam String submittedCode,
            @RequestParam Integer score,
            @RequestParam Boolean passed
    ) {
        return attemptService.saveAttempt(
                userId, challengeId, submittedCode, score, passed
        );
    }

    @GetMapping("/user/{userId}")
    public List<AttemptDto> getUserAttempts(@PathVariable String userId) {
        return attemptService.getUserAttempts(userId);
    }
}
