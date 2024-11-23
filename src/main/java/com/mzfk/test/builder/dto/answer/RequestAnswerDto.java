package com.mzfk.test.builder.dto.answer;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
@AllArgsConstructor
public class RequestAnswerDto {
    @NotBlank(message = "Текст ответа не должен быть пустым")
    private String answerText;

    @NotNull(
            message = "Характер верности ответа должен быть определен" +
                    " (true - ответ верный; false - ответ неверный)")
    private Boolean isCorrect;
}
