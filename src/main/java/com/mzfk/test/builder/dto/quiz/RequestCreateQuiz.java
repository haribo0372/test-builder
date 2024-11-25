package com.mzfk.test.builder.dto.quiz;

import com.mzfk.test.builder.dto.question.RequestCreateQuestionDto;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
@AllArgsConstructor
@Schema(description = "Запрос на создание квиза")
public class RequestCreateQuiz {
    @NotBlank(message = "Название теста не должно быть пустым")
    @Schema(description = "Заголовок квиза")
    private String title;

    @Valid
    @Schema(description = "Множество вариантов ответа")
    private final Set<RequestCreateQuestionDto> questions;

}
