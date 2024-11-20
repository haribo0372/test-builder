package com.mzfk.test.builder.service;

import com.mzfk.test.builder.model.Answer;
import com.mzfk.test.builder.repository.AnswerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class AnswerService {
    private final AnswerRepository answerRepository;

    @Transactional
    public Answer save(Answer answer) {
        Answer savedAnswer = answerRepository.save(answer);
        log.info("{} сохранен", savedAnswer);
        return savedAnswer;
    }

    public Optional<Answer> findById(final Long id) {
        return answerRepository.findById(id);
    }

    public Optional<Answer> findByAnswerText(String answerText) {
        return answerRepository.findByAnswerText(answerText);
    }
}
