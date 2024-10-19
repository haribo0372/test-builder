package com.mzfk.test.builder.repository;

import com.mzfk.test.builder.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {
    Optional<Question> findByQuestionText(String questionText);
}
