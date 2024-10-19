package com.mzfk.test.builder.repository;

import com.mzfk.test.builder.model.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuizRepository extends JpaRepository<Quiz, Long> {
}
