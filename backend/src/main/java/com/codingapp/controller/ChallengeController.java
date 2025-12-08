package com.codingapp.controller;

import com.codingapp.model.Challenge;
import com.codingapp.repository.ChallengeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/challenges")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class ChallengeController {

    private final ChallengeRepository challengeRepository;

    @GetMapping
    public ResponseEntity<List<Challenge>> getAllChallenges() {
        List<Challenge> challenges = challengeRepository.findByIsActiveTrueOrderByDifficultyAsc();
        return ResponseEntity.ok(challenges);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Challenge> getChallengeById(@PathVariable String id) {
        return challengeRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.<Challenge>notFound().build());
    }


    @PostMapping
    public ResponseEntity<Challenge> createChallenge(@RequestBody Challenge challenge) {
        Challenge saved = (Challenge) challengeRepository.save(challenge);
        return ResponseEntity.ok(saved);
    }
}
