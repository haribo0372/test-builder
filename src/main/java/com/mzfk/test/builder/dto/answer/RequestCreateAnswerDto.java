package com.mzfk.test.builder.dto.answer;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
@AllArgsConstructor
@Schema(description = "Запрос на создание варианта ответа")
public class RequestCreateAnswerDto {
    @NotBlank(message = "Текст ответа не должен быть пустым")
    @Schema(description = "Описывающий текст варианта ответа")
    private String answerText;

    @NotNull(
            message = "Характер верности ответа должен быть определен" +
                    " (true - ответ верный; false - ответ неверный)")
    @Schema(description = "Характер верности варианта ответа (true/false)")
    private Boolean isCorrect;
}
