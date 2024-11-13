package com.mzfk.test.builder.dto.quiz;

import com.mzfk.test.builder.dto.question.RequestCreateQuestionDto;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.Set;

@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
@AllArgsConstructor
public class RequestCreateQuiz {
    @NotBlank(message = "Название теста не должно быть пустым")
    private String title;

    private final Set<RequestCreateQuestionDto> questions;

}
