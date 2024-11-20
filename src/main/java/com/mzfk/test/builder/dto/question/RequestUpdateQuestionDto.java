package com.mzfk.test.builder.dto.question;

import com.mzfk.test.builder.dto.answer.RequestAnswerDto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.Set;

@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
@AllArgsConstructor
public class RequestUpdateQuestionDto {
    @NotNull
    private Long id;

    @NotBlank(message = "Текст вопроса не должно быть пустым")
    private String questionText;

    private Set<RequestAnswerDto> answers;
}
