package com.mzfk.test.builder.service;

import com.mzfk.test.builder.model.Quiz;
import com.mzfk.test.builder.repository.QuizRepository;
import com.mzfk.test.builder.util.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class QuizService {
    private final QuizRepository quizRepository;
    private final QuestionService questionService;

    @Transactional
    public Quiz saveQuiz(Quiz quiz) {
//        quiz.getQuestions().forEach(questionService::save);
        Quiz savedQuiz = quizRepository.save(quiz);
        log.info("Квиз с id = {} сохранен", savedQuiz.getId());
        return savedQuiz;
    }

    public Quiz updateQuiz(Quiz quiz){
        Quiz storageQuiz = findById(quiz.getId());
        storageQuiz.setTitle(quiz.getTitle());
        return quizRepository.save(storageQuiz);
    }

    @Transactional
    public void deleteQuiz(Quiz quiz) {
        quizRepository.delete(quiz);
        log.info("Квиз с id = {} удален", quiz.getId());
    }

    public Quiz findById(Long id) {
        Quiz foundQuiz = quizRepository.findById(id).orElseThrow(() ->
                new NotFoundException(String.format("Квиз с id=%d не найден", id)));
        log.info("Квиз с id={} успешно найден", id);
        return foundQuiz;
    }
}
