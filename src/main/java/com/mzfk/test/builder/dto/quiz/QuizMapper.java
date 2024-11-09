package com.mzfk.test.builder.dto.quiz;

import com.mzfk.test.builder.dto.question.QuestionMapper;
import com.mzfk.test.builder.dto.question.ResponseQuestionDto;
import com.mzfk.test.builder.model.Quiz;

import java.util.Set;
import java.util.stream.Collectors;

public class QuizMapper {

    public static Quiz fromDto(RequestCreateQuiz requestQuiz) {
        Quiz quiz = new Quiz(null, requestQuiz.getTitle());
        requestQuiz.getQuestions()
                .stream()
                .map(QuestionMapper::fromDto).forEach(quiz::addQuestion);

        return quiz;
    }

    public static Quiz fromDto(RequestUpdateQuiz requestQuiz) {
        return new Quiz(requestQuiz.getId(), requestQuiz.getTitle());
    }

    public static ResponseQuizDto toDto(Quiz quiz) {
        Set<ResponseQuestionDto> questionDtoSet = quiz.getQuestions()
                .stream()
                .map(QuestionMapper::toDto)
                .collect(Collectors.toSet());
        return new ResponseQuizDto(quiz.getId(), quiz.getTitle(), questionDtoSet);
    }
}
