package com.mzfk.testBuilder.repository;

import com.mzfk.testBuilder.model.Answer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnswerRepository extends JpaRepository<Answer, Long> {
}
