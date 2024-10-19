package com.mzfk.test.builder.service;

import com.mzfk.test.builder.model.Question;
import com.mzfk.test.builder.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class QuestionService {
    private final QuestionRepository questionRepository;
    private final AnswerService answerService;

    public Optional<Question> findById(final Long id) {
        return questionRepository.findById(id);
    }

    public Optional<Question> findByQuestionText(String questionText) {
        return questionRepository.findByQuestionText(questionText);
    }

    public void save(final Question question) {
        question.getAnswers().forEach(answerService::save);
        Question savedQuestion = questionRepository.save(question);
        log.info("Вопрос с id={} сохранен", savedQuestion.getId());
    }

    public void delete(final Question question){
        questionRepository.delete(question);
        log.info("Вопрос с id={} удален", question.getId());
    }

}
