package com.mzfk.test.builder.dto.answer;

import com.mzfk.test.builder.model.Answer;

public class AnswerMapper {
    public static Answer fromDto(RequestCreateAnswerDto answerDto) {
        return new Answer(null, answerDto.getAnswerText(), answerDto.getIsCorrect());
    }

    public static Answer fromDto(RequestUpdateAnswerDto answerDto) {
        return new Answer(answerDto.getId(), answerDto.getAnswerText(), answerDto.getIsCorrect());
    }

    public static ResponseAnswerDto toDto(Answer answer) {
        return new ResponseAnswerDto(
                answer.getId(),
                answer.getAnswerText(),
                answer.getIsCorrect());
    }
}
