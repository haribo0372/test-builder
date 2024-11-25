package com.mzfk.test.builder.dto.question;

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
@Schema(description = "Запрос на обновление вопроса")
public class RequestUpdateQuestionDto {
    @NotNull(message = "Id должен быть указан")
    @Schema(description = "Идентификатор по которому будет идентифицироваться вопрос из БД, который нужно обновить")
    private Long id;

    @NotBlank(message = "Текст вопроса не должно быть пустым")
    private String questionText;
}
