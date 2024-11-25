package com.mzfk.test.builder.dto.quiz;

import com.mzfk.test.builder.dto.question.ResponseQuestionDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Set;

@Data
@AllArgsConstructor
@Schema(description = "Модель квиз для ответа пользователю")
public class ResponseQuizDto {
    private Long id;
    private String title;
    private Set<ResponseQuestionDto> questions;
}