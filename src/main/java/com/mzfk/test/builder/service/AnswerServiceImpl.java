package com.mzfk.test.builder.service;

import com.mzfk.test.builder.model.Answer;
import com.mzfk.test.builder.model.Question;
import com.mzfk.test.builder.service.base.answer.AnswerService;
import com.mzfk.test.builder.util.exception.ValidateException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.function.Predicate;

import static java.lang.String.format;

@Slf4j
@Service
public class AnswerServiceImpl implements AnswerService {

    @Override
    public Answer findById(Question question, Long answerId) {
        return findInQuestionByPredicate(question, answer -> answer.getId().equals(answerId)).orElseThrow(() -> {
            String message = format("Answer{id=%d} не найден у Question{id=%d}", answerId, question.getId());
            log.warn(message);
            return new ValidateException(message);
        });
    }

    @Override
    public Optional<Answer> findByAnswerText(Question question, String answerText) {
        return findInQuestionByPredicate(question, answer -> answer.getAnswerText().equals(answerText));
    }

    @Override
    public void create(Question question, Answer answer) {
        findByAnswerText(question, answer.getAnswerText()).ifPresent(i -> {
            String message = format("Вариант ответа %s у Question{id=%d} уже существует",
                    answer.getAnswerText(), question.getId());
            log.warn(message);
            throw new ValidateException(message);
        });

        question.getAnswers().add(answer);
        answer.setQuestion(question);
    }

    @Override
    public void update(Question question, Answer answer) {
        findByAnswerText(question, answer.getAnswerText()).ifPresent(i -> {
            String message = format("Вариант ответа %s у Question{id=%d} уже существует",
                    answer.getAnswerText(), question.getId());
            log.warn(message);
            throw new ValidateException(message);
        });

        Answer answerOfQuestion = findById(question, answer.getId());
        answerOfQuestion.setAnswerText(answer.getAnswerText());
    }

    @Override
    public void delete(Question question, Long answerId) {
        question.getAnswers().remove(findById(question, answerId));
    }

    private Optional<Answer> findInQuestionByPredicate(Question question, Predicate<Answer> predicate) {
        return question.getAnswers().stream().filter(predicate).findAny();
    }
}
