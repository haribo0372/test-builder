package com.mzfk.test.builder.dto.question;

import com.mzfk.test.builder.dto.answer.ResponseAnswerDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Set;

@Data
@AllArgsConstructor
@Schema(description = "Модель вопроса для ответа пользователю")
public class ResponseQuestionDto {
    private Long id;
    private String questionText;
    private Set<ResponseAnswerDto> answers;
}