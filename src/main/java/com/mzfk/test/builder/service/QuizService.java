package com.mzfk.test.builder.service;

import com.mzfk.test.builder.model.Quiz;
import com.mzfk.test.builder.repository.QuizRepository;
import com.mzfk.test.builder.util.exception.NotFoundException;
import com.mzfk.test.builder.util.exception.ValidateException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class QuizService {
    private final QuizRepository quizRepository;
    private final QuestionService questionService;

    public Quiz saveQuiz(Quiz quiz){
        quiz.getQuestions().forEach(questionService::save);
        return quizRepository.save(quiz);
    }

    public void update(Quiz quiz){
        if (quiz.getId() == null)
            throw new ValidateException("Для обновления квиза, id должен быть указан");

//        quiz.getQuestions().forEach(questionService::update);
    }
    public Quiz findById(Long id){
        return quizRepository.findById(id).orElseThrow(() ->
                new NotFoundException(String.format("Квиз с id=%d не найден", id)));
    }
}
