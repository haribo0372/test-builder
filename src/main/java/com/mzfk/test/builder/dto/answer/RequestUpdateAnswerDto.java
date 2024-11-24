package com.mzfk.test.builder.dto.answer;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RequestUpdateAnswerDto {
    @NotNull(message = "Id должен быть указан")
    private Long id;

    @NotBlank(message = "Текст ответа не должен быть пустым")
    private String answerText;

    @NotNull(
            message = "Характер верности ответа должен быть определен" +
                    " (true - ответ верный; false - ответ неверный)")
    private Boolean isCorrect;
}
