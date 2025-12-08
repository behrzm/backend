package com.codingapp.config;

import com.codingapp.model.Challenge;
import com.codingapp.model.User;
import com.codingapp.repository.ChallengeRepository;
import com.codingapp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final ChallengeRepository challengeRepository;
    private final UserRepository userRepository;

    @Override
    public void run(String... args) {
        // Only initialize if database is empty
        if (challengeRepository.count() == 0) {
            initializeChallenges();
        }

        if (userRepository.count() == 0) {
            initializeDemoUser();
        }
    }

    private void initializeChallenges() {
        // Challenge 1: Hello World
        Challenge hello = new Challenge();
        hello.setTitle("Hello World");
        hello.setDescription("Write a program that prints 'Hello, World!' to the console.");
        hello.setDifficulty(Challenge.Difficulty.EASY);
        hello.setCategory("basics");
        hello.setBasePoints(50);
        hello.setStarterCode("fun main() {\n    // Your code here\n}");
        challengeRepository.save(hello);

        // Challenge 2: Sum Two Numbers
        Challenge sum = new Challenge();
        sum.setTitle("Sum Two Numbers");
        sum.setDescription("Create a function that returns the sum of two integers.");
        sum.setDifficulty(Challenge.Difficulty.EASY);
        sum.setCategory("basics");
        sum.setBasePoints(75);
        sum.setStarterCode("fun sum(a: Int, b: Int): Int {\n    // Your code here\n    return 0\n}");
        challengeRepository.save(sum);

        // Challenge 3: Reverse String
        Challenge reverse = new Challenge();
        reverse.setTitle("Reverse String");
        reverse.setDescription("Reverse a given string without using built-in functions.");
        reverse.setDifficulty(Challenge.Difficulty.MEDIUM);
        reverse.setCategory("strings");
        reverse.setBasePoints(100);
        reverse.setStarterCode("fun reverse(s: String): String {\n    // Your code here\n    return \"\"\n}");
        challengeRepository.save(reverse);

        // Challenge 4: Find Maximum
        Challenge findMax = new Challenge();
        findMax.setTitle("Find Maximum");
        findMax.setDescription("Find the maximum element in an array of integers.");
        findMax.setDifficulty(Challenge.Difficulty.MEDIUM);
        findMax.setCategory("arrays");
        findMax.setBasePoints(100);
        findMax.setStarterCode("fun findMax(arr: IntArray): Int {\n    // Your code here\n    return 0\n}");
        challengeRepository.save(findMax);

        // Challenge 5: Binary Search
        Challenge binarySearch = new Challenge();
        binarySearch.setTitle("Binary Search");
        binarySearch.setDescription("Implement binary search algorithm to find a target element.");
        binarySearch.setDifficulty(Challenge.Difficulty.HARD);
        binarySearch.setCategory("algorithms");
        binarySearch.setBasePoints(150);
        binarySearch.setStarterCode("fun binarySearch(arr: IntArray, target: Int): Int {\n    // Your code here\n    return -1\n}");
        challengeRepository.save(binarySearch);

        System.out.println("✅ Initialized 5 sample challenges");
    }

    private void initializeDemoUser() {
        User demo = new User();
        demo.setEmail("demo@codingapp.com");
        demo.setDisplayName("Demo User");
        demo.setFirebaseUid("demo-firebase-uid");
        demo.setLevel(1);
        demo.setTotalPoints(0);
        userRepository.save(demo);

        System.out.println("✅ Created demo user");
    }}