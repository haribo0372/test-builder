package com.mzfk.test.builder.service;

import com.mzfk.test.builder.model.Quiz;
import com.mzfk.test.builder.repository.QuizRepository;
import com.mzfk.test.builder.util.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class QuizService {
    private final QuizRepository quizRepository;
    private final QuestionService questionService;

    public Quiz saveQuiz(Quiz quiz) {
        quiz.getQuestions().forEach(questionService::save);
        return quizRepository.save(quiz);
    }

    public void deleteQuiz(Long id) {
        Quiz quiz = findById(id);
        quiz.getQuestions().forEach(questionService::delete);
        quizRepository.delete(quiz);
    }

    public Quiz findById(Long id) {
        return quizRepository.findById(id).orElseThrow(() ->
                new NotFoundException(String.format("Квиз с id=%d не найден", id)));
    }
}
