package com.mzfk.test.builder.dto.answer;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Schema(description = "Запрос на обновление варианта ответа")
public class RequestUpdateAnswerDto {
    @NotNull(message = "Id должен быть указан")
    @Schema(description = "Идентификатор по которому будет идентифицироваться вариант ответа из БД, который нужно обновить")
    private Long id;

    @NotBlank(message = "Текст ответа не должен быть пустым")
    @Schema(description = "Описывающий текст варианта ответа")
    private String answerText;

    @NotNull(
            message = "Характер верности ответа должен быть определен" +
                    " (true - ответ верный; false - ответ неверный)")
    @Schema(description = "Характер верности варианта ответа (true/false)")
    private Boolean isCorrect;
}
