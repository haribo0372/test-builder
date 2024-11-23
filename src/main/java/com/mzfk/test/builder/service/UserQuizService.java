package com.mzfk.test.builder.service;

import com.mzfk.test.builder.model.Answer;
import com.mzfk.test.builder.model.Question;
import com.mzfk.test.builder.model.Quiz;
import com.mzfk.test.builder.model.User;
import com.mzfk.test.builder.util.exception.AccessException;
import com.mzfk.test.builder.util.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor
public class UserQuizService {
    private final UserService userService;
    private final QuizService quizService;

    public Quiz saveQuiz(final Quiz quiz) {
        quiz.setUser(currentUser());
        final Quiz savedQuiz = quizService.saveQuiz(quiz);
        currentUser().addQuiz(quiz);
        userService.save(currentUser());
        return savedQuiz;
    }

    public Quiz updateQuiz(final Quiz quiz){
        getQuizById(quiz.getId());
        return quizService.updateQuiz(quiz);
    }

    public Quiz updateQuizQuestion(final Long quizId, final Question question) {
        Question storageQuestion = getQuestionById(quizId, question.getId());
        System.out.println("ANSWERS : " + question.getAnswers());
        System.out.println("TITLE : " + question.getQuestionText());
        System.out.println("Quiestion = " + storageQuestion.getId() + " его Quiz " + storageQuestion.getQuiz().getId());
        Quiz quiz = getQuizById(quizId);
        quiz.addQuestion(storageQuestion);

        return saveQuiz(quiz);
    }

    public Quiz getQuizById(Long quizId) {
        Quiz foundQuiz = quizService.findById(quizId);
        currentUser().getQuizzes()
                .stream()
                .filter(i -> i.getId().equals(quizId))
                .findAny().orElseThrow(() ->
                        new AccessException("Нет прав на просмотр квиза"));

        return foundQuiz;
    }

    public void deleteQuizById(Long quizId) {
        Quiz quiz = getQuizById(quizId);
        currentUser().getQuizzes().remove(quiz);
        userService.save(currentUser());
    }

    public void deleteQuestionById(Long quizId, Long questionId) {
        Quiz quiz = getQuizById(quizId);
        Question question = getQuestionById(quizId, questionId);
        quiz.getQuestions().remove(question);
        saveQuiz(quiz);
    }

    public Question getQuestionById(Long quizId, Long questionId) {
        Quiz quiz = getQuizById(quizId);
        return quiz.getQuestions()
                .stream()
                .filter(question -> question.getId().equals(questionId))
                .findAny().orElseThrow(() ->
                        new NotFoundException(
                                format("Вопроса с id=%d у квиза с id=%d не существует", quizId, questionId)));
    }

    public Quiz addQuestionToQuiz(Long quizId, Question question) {
        Quiz quiz = getQuizById(quizId);
        quiz.addQuestion(question);
        return saveQuiz(quiz);
    }

    public Quiz addAnswerToQuestion(Long quizId, Long questionId, Answer answer) {
        Quiz quiz = getQuizById(quizId);
        Question question = getQuestionById(quizId, questionId);
        question.addAnswer(answer);
        return saveQuiz(quiz);
    }

    public Collection<Quiz> getAllQuizzes() {
        return currentUser().getQuizzes();
    }

    public User currentUser() {
        return userService.getCurrentUser();
    }
}
