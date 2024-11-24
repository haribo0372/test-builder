package com.mzfk.test.builder.dto.answer;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
@AllArgsConstructor
public class RequestCreateAnswerDto {
    @NotBlank(message = "Текст ответа не должен быть пустым")
    private String answerText;

    @NotNull(
            message = "Характер верности ответа должен быть определен" +
                    " (true - ответ верный; false - ответ неверный)")
    private Boolean isCorrect;
}
