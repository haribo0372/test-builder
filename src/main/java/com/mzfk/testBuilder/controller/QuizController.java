package com.mzfk.testBuilder.controller;

import com.mzfk.testBuilder.model.Quiz;
import com.mzfk.testBuilder.repository.QuizRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/quiz")
public class QuizController {
    private final QuizRepository quizRepository;

    @PostMapping
    public Quiz createNewQuiz(final Quiz quiz){
        quizRepository.save(quiz);
    }
}
