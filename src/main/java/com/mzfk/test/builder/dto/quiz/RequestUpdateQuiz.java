package com.mzfk.test.builder.dto.quiz;

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
@Schema(description = "Запрос на обновление квиза")
public class RequestUpdateQuiz {
    @NotNull(message = "id должен быть задан")
    @Schema(description = "Идентификатор по которому будет идентифицироваться квиз из БД, который нужно обновить")
    private Long id;

    @NotBlank(message = "Название квиза не должно быть пустым")
    @Schema(description = "Измененный заголовок квиза")
    private String title;
}