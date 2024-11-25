package com.mzfk.test.builder.service;

import com.mzfk.test.builder.model.Question;
import com.mzfk.test.builder.model.Quiz;
import com.mzfk.test.builder.service.base.question.QuestionService;
import com.mzfk.test.builder.util.exception.ValidateException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.function.Predicate;

import static java.lang.String.format;

@Slf4j
@Service
public class QuestionServiceImpl implements QuestionService {
    @Override
    public Question findById(Quiz quiz, Long questionId) {
        return findInQuizByPredicateQuestion(quiz, question -> question.getId().equals(questionId)).orElseThrow(() -> {
            String message = format("Question{id=%d} не найден у Quiz{id=%d}", questionId, quiz.getId());
            log.warn(message);
            return new ValidateException(message);
        });
    }

    @Override
    public Optional<Question> findByQuestionText(Quiz quiz, String questionText) {
        return findInQuizByPredicateQuestion(quiz, question -> question.getQuestionText().equals(questionText));
    }

    @Override
    public void create(Quiz quiz, Question question) {
        findByQuestionText(quiz, question.getQuestionText()).ifPresent(i -> {
            String message = format("Вопрос %s у Quiz{id=%d} уже существует",
                    question.getQuestionText(), quiz.getId());
            log.warn(message);
            throw new ValidateException(message);
        });

        quiz.addQuestion(question);
    }

    @Override
    public void update(Quiz quiz, Question question) {
        findByQuestionText(quiz, question.getQuestionText()).ifPresent(i -> {
            String message = format("Вопрос %s у Quiz{id=%d} уже существует",
                    question.getQuestionText(), quiz.getId());
            log.warn(message);
            throw new ValidateException(message);
        });

        Question questionOfQuiz = this.findById(quiz, question.getId());
        questionOfQuiz.setQuestionText(question.getQuestionText());
    }

    @Override
    public void delete(Quiz quiz, Long questionId) {
        quiz.removeQuestion(findById(quiz, questionId));
    }

    private Optional<Question> findInQuizByPredicateQuestion(Quiz quiz, Predicate<Question> predicate) {
        return quiz.getQuestions().stream().filter(predicate).findAny();
    }
}
