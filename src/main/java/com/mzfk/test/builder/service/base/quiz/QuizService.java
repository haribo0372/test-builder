package com.mzfk.test.builder.service.base.quiz;

import com.mzfk.test.builder.model.Quiz;

import java.util.Collection;

public interface QuizService {
    Quiz create(Quiz entity);

    Quiz update(Quiz entity);

    Quiz findById(Long id);

    Collection<Quiz> findAll();
}
