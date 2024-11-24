package com.mzfk.test.builder.dto.question;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
@AllArgsConstructor
public class RequestUpdateQuestionDto {
    @NotNull(message = "Id должен быть указан")
    private Long id;

    @NotBlank(message = "Текст вопроса не должно быть пустым")
    private String questionText;
}
