package com.mzfk.test.builder.service.base.answer;

import com.mzfk.test.builder.model.Answer;
import com.mzfk.test.builder.model.Question;

import java.util.Optional;

public interface AnswerService {
    Answer findById(Question question, Long answerId);

    Optional<Answer> findByAnswerText(Question question, String answerText);

    void create(Question question, Answer answer);

    void update(Question question, Answer answer);

    void delete(Question question, Long answerId);
}
