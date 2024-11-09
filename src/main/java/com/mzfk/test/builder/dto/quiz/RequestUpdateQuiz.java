package com.mzfk.test.builder.dto.quiz;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
@AllArgsConstructor
public class RequestUpdateQuiz {
    @NotNull(message = "id должен быть задан")
    private Long id;

    @NotBlank(message = "Название теста не должно быть пустым")
    private String title;
}