package com.mzfk.test.builder.dto.quiz;

import com.mzfk.test.builder.dto.quiz.simply.QuestionDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;

@Data
@Schema(description = "Запрос на создание квиза")
public class QuizRequest {
    @Schema(description = "Название квиза", example = "My_Quiz")
    @Size(min = 3, max = 50, message = "Название квиза должно содержать от 3 до 50 символов")
    @NotBlank(message = "Название квиза не может быть пустыми")
    private String title;

    @Schema(description = "Название квиза")
    private List<QuestionDTO> questions;
}
