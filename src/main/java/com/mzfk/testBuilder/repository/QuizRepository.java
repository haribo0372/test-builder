package com.mzfk.testBuilder.repository;

import com.mzfk.testBuilder.model.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuizRepository extends JpaRepository<Quiz, Long> {
}