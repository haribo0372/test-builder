package com.mzfk.test.builder.dto.question;


import com.mzfk.test.builder.dto.answer.AnswerMapper;
import com.mzfk.test.builder.dto.answer.ResponseAnswerDto;
import com.mzfk.test.builder.model.Answer;
import com.mzfk.test.builder.model.Question;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class QuestionMapper {

    public static Question fromDto(RequestCreateQuestionDto questionDto) {
        Question question = new Question(null, questionDto.getQuestionText());
        questionDto.getAnswers()
                .stream()
                .map(AnswerMapper::fromDto)
                .forEach(question::addAnswer);

        return question;
    }

    public static Question fromDto(RequestUpdateQuestionDto questionDto) {
        Set<Answer> answers = questionDto.getAnswers() == null ? null : questionDto.getAnswers()
                .stream()
                .map(AnswerMapper::fromDto)
                .collect(Collectors.toSet());

        return new Question(questionDto.getId(), questionDto.getQuestionText(), answers);
    }

    public static ResponseQuestionDto toDto(Question question) {
        Set<ResponseAnswerDto> answerDtoSet = question.getAnswers()
                .stream()
                .map(AnswerMapper::toDto)
                .collect(Collectors.toSet());

        return new ResponseQuestionDto(question.getId(), question.getQuestionText(), answerDtoSet);
    }
}
