package com.mzfk.test.builder.repository;

import com.mzfk.test.builder.model.Answer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AnswerRepository extends JpaRepository<Answer, Long> {
    Optional<Answer> findByAnswerText(String answerText);
}