package com.mzfk.test.builder.dto.answer;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Schema(description = "Модель варианта ответа для ответа пользователю")
public class ResponseAnswerDto {
    private Long id;
    private String answerText;
    private Boolean isCorrect;
}
