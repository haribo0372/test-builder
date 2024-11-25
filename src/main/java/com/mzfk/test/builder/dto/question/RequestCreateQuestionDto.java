package com.mzfk.test.builder.dto.question;

import com.mzfk.test.builder.dto.answer.RequestCreateAnswerDto;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
@AllArgsConstructor
@Schema(description = "Запрос на создание вопроса")
public class RequestCreateQuestionDto {
    @NotBlank(message = "Текст вопроса не должно быть пустым")
    @Schema(description = "Описывающий текст вопроса")
    private String questionText;

    @Valid
    @Schema(description = "Множество вариантов ответа")
    private Set<RequestCreateAnswerDto> answers;
}