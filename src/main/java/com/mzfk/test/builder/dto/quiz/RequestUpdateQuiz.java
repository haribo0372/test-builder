package com.mzfk.test.builder.dto.quiz;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
@AllArgsConstructor
public class RequestUpdateQuiz {
    @NotNull(message = "id должен быть задан")
    private Long id;

    @NotBlank(message = "Название квиза не должно быть пустым")
    private String title;
}