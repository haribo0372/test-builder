package com.mzfk.test.builder.service.base.question;

import com.mzfk.test.builder.model.Question;
import com.mzfk.test.builder.model.Quiz;

import java.util.Optional;

public interface QuestionService {
    Question findById(Quiz quiz, Long questionId);

    Optional<Question> findByQuestionText(Quiz quiz, String questionText);

    void create(Quiz quiz, Question question);

    void update(Quiz quiz, Question question);

    void delete(Quiz quiz, Long questionId);
}
