package com.mzfk.test.builder.service.base.user;

import com.mzfk.test.builder.model.Answer;
import com.mzfk.test.builder.model.Question;
import com.mzfk.test.builder.model.Quiz;

import java.util.Collection;

public interface UserQuizService {
    Collection<Quiz> getAllQuizzes();

    Quiz getQuizById(Long quizId);

    Quiz saveQuiz(final Quiz quiz);

    Quiz updateQuiz(final Quiz quiz);

    void deleteQuizById(final Long quizId);

    Question getQuestionById(Long quizId, Long questionId);

    Quiz addQuestionToQuiz(Long quizId, Question question);

    Quiz updateQuestion(final Long quizId, final Question question);

    void deleteQuestionById(Long quizId, Long questionId);

    Answer getAnswerById(Long quizId, Long questionId, Long answerId);

    Quiz addAnswerToQuestion(Long quizId, Long questionId, Answer answer);

    Quiz updateAnswer(Long quizId, Long questionId, Answer answer);

    void deleteAnswerById(Long quizId, Long questionId, Long answerId);
}
