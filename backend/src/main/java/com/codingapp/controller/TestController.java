package com.codingapp.controller;

import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/api/test")
@CrossOrigin(origins = "*")
public class TestController {

    @GetMapping("/hello")
    public Map<String, String> hello() {
        return Map.of(
                "message", "Backend is working!",
                "status", "success"
        );
    }
}