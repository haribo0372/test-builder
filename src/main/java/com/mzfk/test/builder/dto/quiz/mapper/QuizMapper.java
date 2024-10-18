package com.mzfk.test.builder.dto.quiz.mapper;

import com.mzfk.test.builder.dto.quiz.QuizRequest;
import com.mzfk.test.builder.dto.quiz.QuizResponse;
import com.mzfk.test.builder.dto.quiz.simply.AnswerDTO;
import com.mzfk.test.builder.dto.quiz.simply.QuestionDTO;
import com.mzfk.test.builder.model.Answer;
import com.mzfk.test.builder.model.Question;
import com.mzfk.test.builder.model.QuestionAnswer;
import com.mzfk.test.builder.model.Quiz;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class QuizMapper {
    public Quiz toQuiz(QuizRequest quizDTO) {
        Quiz quiz = Quiz.builder()
                .title(quizDTO.getTitle())
                .build();

        quizDTO.getQuestions().forEach(questionDTO -> {
            Question question = Question.builder()
                    .questionText(questionDTO.getQuestion())
                    .build();

            questionDTO.getAnswers().forEach(answerDTO -> {
                Answer answer = Answer.builder()
                        .answerText(answerDTO.getAnswer())
                        .build();

                QuestionAnswer questionAnswer = QuestionAnswer.builder()
                        .question(question)
                        .answer(answer)
                        .isCorrect(answerDTO.is_correct())
                        .build();

                question.addQuestionAnswer(questionAnswer);
                answer.addQuestionAnswer(questionAnswer);
            });

            quiz.addQuestion(question);
        });

        return quiz;
    }

    public Quiz toQuiz(QuizResponse quizResponse){
        Quiz quiz = Quiz.builder()
                .id(quizResponse.getId())
                .title(quizResponse.getTitle())
                .build();

        quizResponse.getQuestions().forEach(questionDTO -> {
            Question question = Question.builder()
                    .id(questionDTO.getId())
                    .questionText(questionDTO.getQuestion())
                    .build();

            questionDTO.getAnswers().forEach(answerDTO -> {
                Answer answer = Answer.builder()
                        .id(answerDTO.getId())
                        .answerText(answerDTO.getAnswer())
                        .build();

                QuestionAnswer questionAnswer = QuestionAnswer.builder()
                        .question(question)
                        .answer(answer)
                        .isCorrect(answerDTO.is_correct())
                        .build();

                question.addQuestionAnswer(questionAnswer);
                answer.addQuestionAnswer(questionAnswer);
            });

            quiz.addQuestion(question);
        });

        return quiz;
    }

    public QuizResponse toDto(Quiz quiz) {
        final List<QuestionDTO> questionDTOList = new ArrayList<>();

        quiz.getQuestions().forEach(question -> {
            List<AnswerDTO> answerDTOList = new ArrayList<>();
            question.getQuestionAnswers().forEach(questionAnswer -> {
                Answer currentAnswer = questionAnswer.getAnswer();
                AnswerDTO answerDTO = new AnswerDTO(
                        currentAnswer.getAnswerText(), questionAnswer.isCorrect()
                );
                answerDTO.setId(currentAnswer.getId());
                answerDTOList.add(answerDTO);
            });

            QuestionDTO questionDTO = new QuestionDTO(question.getQuestionText(), answerDTOList);
            questionDTO.setId(question.getId());
            questionDTOList.add(questionDTO);
        });

        return new QuizResponse(quiz.getId(), quiz.getTitle(), questionDTOList);
    }
}
