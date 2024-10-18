package com.mzfk.test.builder.service;

import com.mzfk.test.builder.model.Quiz;
import com.mzfk.test.builder.model.User;
import com.mzfk.test.builder.util.exception.AccessException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@RequiredArgsConstructor
public class UserQuizService {
    private final UserService userService;
    private final QuizService quizService;

    public Quiz saveQuiz(final Quiz quiz) {
        final Quiz savedQuiz = quizService.saveQuiz(quiz);
        currentUser().addQuiz(savedQuiz);
        userService.save(currentUser());
        return savedQuiz;
    }

    public void updateQuiz(final Long preUserId, Quiz quiz) {


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

    public Collection<Quiz> getAllQuizzes() {
        return currentUser().getQuizzes();
    }

    public User currentUser() {
        return userService.getCurrentUser();
    }
}
