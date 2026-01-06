package com.codingapp.repository;

import com.codingapp.model.Attempt;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AttemptRepository extends JpaRepository<Attempt, String> {

    List<Attempt> findByUserId(String userId);
}
