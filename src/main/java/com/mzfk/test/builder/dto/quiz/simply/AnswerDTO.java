package com.mzfk.test.builder.dto.quiz.simply;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class AnswerDTO {
    private Long id;
    private final String answer;
    private final boolean is_correct;
}
