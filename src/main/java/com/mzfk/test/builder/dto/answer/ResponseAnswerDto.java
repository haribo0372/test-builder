package com.mzfk.test.builder.dto.answer;

import lombok.*;

@Data
@AllArgsConstructor
public class ResponseAnswerDto {
    private Long id;
    private String answerText;
    private Boolean isCorrect;
}
