package com.mzfk.test.builder.dto.question;

import com.mzfk.test.builder.dto.answer.RequestCreateAnswerDto;
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
public class RequestCreateQuestionDto {
    @NotBlank(message = "Текст вопроса не должно быть пустым")
    private String questionText;

    @Valid
    private Set<RequestCreateAnswerDto> answers;
}