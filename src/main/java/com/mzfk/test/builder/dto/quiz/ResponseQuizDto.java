package com.mzfk.test.builder.dto.quiz;

import com.mzfk.test.builder.dto.question.ResponseQuestionDto;
import lombok.*;

import java.util.Set;

@Data
@AllArgsConstructor
public class ResponseQuizDto {
    private Long id;
    private String title;
    private Set<ResponseQuestionDto> questions;
}