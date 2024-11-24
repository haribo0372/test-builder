package com.mzfk.test.builder.service;

import com.mzfk.test.builder.model.Answer;
import com.mzfk.test.builder.model.Question;
import com.mzfk.test.builder.model.Quiz;
import com.mzfk.test.builder.model.User;
import com.mzfk.test.builder.service.base.answer.AnswerService;
import com.mzfk.test.builder.service.base.question.QuestionService;
import com.mzfk.test.builder.service.base.quiz.QuizService;
import com.mzfk.test.builder.service.base.user.UserQuizService;
import com.mzfk.test.builder.util.exception.AccessException;
import com.mzfk.test.builder.util.exception.EntitySaveException;
import com.mzfk.test.builder.util.exception.ValidateException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

import static java.lang.String.format;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserQuizServiceImpl implements UserQuizService {
    private final UserServiceImpl userService;
    private final QuizService quizService;
    private final QuestionService questionService;
    private final AnswerService answerService;

    @Override
    public Collection<Quiz> getAllQuizzes() {
        return currentUser().getQuizzes();
    }

    @Override
    public Quiz getQuizById(Long quizId) {
        Quiz foundQuiz = quizService.findById(quizId);
        currentUser().getQuizzes()
                .stream()
                .filter(i -> i.getId().equals(quizId))
                .findAny().orElseThrow(() ->
                        new AccessException("Нет прав на просмотр квиза"));

        return foundQuiz;
    }

    @Override
    public Quiz saveQuiz(final Quiz quiz) {
        User currentUser = currentUser();
        findQuizOfUserByTitle(quiz.getTitle())
                .ifPresent((i) -> {
                    String message = format("Quiz с именем %s уже существует у User{id=%d}",
                            quiz.getTitle(), currentUser.getId());
                    log.warn(message);
                    throw new ValidateException(message);
                });

        quiz.setUser(currentUser);
        currentUser.addQuiz(quiz);
        userService.save(currentUser);
        return findQuizOfUserByTitle(quiz.getTitle()).orElseThrow(() -> {
            String message = format("Не удалось сохранить Quiz{title=%s} у User{id=%d}", quiz.getTitle(), currentUser.getId());
            log.warn(message);
            return new EntitySaveException(message);
        });
    }

    @Override
    public Quiz updateQuiz(final Quiz quiz) {
        getQuizById(quiz.getId());
        quizService.update(quiz);
        userService.save(currentUser());
        return getQuizById(quiz.getId());
    }

    @Override
    public void deleteQuizById(final Long quizId) {
        Quiz quiz = getQuizById(quizId);
        currentUser().getQuizzes().remove(quiz);
        userService.save(currentUser());
    }

    @Override
    public Question getQuestionById(Long quizId, Long questionId) {
        return questionService.findById(getQuizById(quizId), questionId);
    }

    @Override
    public Quiz addQuestionToQuiz(Long quizId, Question question) {
        Quiz quiz = getQuizById(quizId);
        questionService.create(quiz, question);
        userService.save(currentUser());
        return getQuizById(quizId);
    }

    @Override
    public Quiz updateQuestion(final Long quizId, final Question question) {
        Quiz quiz = getQuizById(quizId);
        questionService.update(quiz, question);

        userService.save(currentUser());
        return quiz;
    }

    @Override
    public void deleteQuestionById(Long quizId, Long questionId) {
        Quiz quiz = getQuizById(quizId);
        questionService.delete(quiz, questionId);
        userService.save(currentUser());
    }

    @Override
    public Answer getAnswerById(Long quizId, Long questionId, Long answerId) {
        return answerService.findById(getQuestionById(quizId, questionId), answerId);
    }

    @Override
    public Quiz addAnswerToQuestion(Long quizId, Long questionId, Answer answer) {
        Question question = getQuestionById(quizId, questionId);
        answerService.create(question, answer);
        userService.save(currentUser());
        return getQuizById(quizId);
    }

    @Override
    public Quiz updateAnswer(Long quizId, Long questionId, Answer answer) {
        Question question = getQuestionById(quizId, questionId);
        answerService.update(question, answer);
        userService.save(currentUser());
        return getQuizById(quizId);
    }

    @Override
    public void deleteAnswerById(Long quizId, Long questionId, Long answerId) {
        Question question = getQuestionById(quizId, questionId);
        answerService.delete(question, answerId);
        userService.save(currentUser());
    }

    private User currentUser() {
        return userService.getCurrentUser();
    }

    private Optional<Quiz> findQuizOfUserByTitle(String title) {
        return currentUser().getQuizzes().stream()
                .filter(q -> q.getTitle().equals(title))
                .findAny();
    }
}