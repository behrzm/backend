package com.codingapp.controller;

import com.codingapp.model.User;
import com.codingapp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class UserController {

    private final UserRepository userRepository;

    @PostMapping("/create")
    public ResponseEntity<User> createUser(@RequestBody Map<String, String> request) {
        User user = new User();
        user.setEmail(request.get("email"));
        user.setDisplayName(request.get("displayName"));
        user.setFirebaseUid(request.getOrDefault("firebaseUid", "demo-uid-" + System.currentTimeMillis()));

        User saved = (User) userRepository.save(user);
        return ResponseEntity.ok(saved);
    }

    @GetMapping("/{id}")
    public Object getUserById(@PathVariable String id) {
        return userRepository.findById(id)
                .map(user -> ResponseEntity.ok((User) user))
                .orElseGet(() -> ResponseEntity.<User>notFound().build());
    }


    @GetMapping
    public ResponseEntity<?> getAllUsers() {
        return ResponseEntity.ok(userRepository.findAll());
    }
}
