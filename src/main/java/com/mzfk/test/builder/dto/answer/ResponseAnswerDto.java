package com.mzfk.test.builder.dto.answer;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResponseAnswerDto {
    private Long id;
    private String answerText;
    private Boolean isCorrect;
}
