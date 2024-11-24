package com.mzfk.test.builder.dto.question;

import com.mzfk.test.builder.dto.answer.ResponseAnswerDto;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Set;

@Data
@AllArgsConstructor
public class ResponseQuestionDto {
    private Long id;
    private String questionText;
    private Set<ResponseAnswerDto> answers;
}