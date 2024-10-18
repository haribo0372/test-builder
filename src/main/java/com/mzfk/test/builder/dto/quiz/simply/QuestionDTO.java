package com.mzfk.test.builder.dto.quiz.simply;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@RequiredArgsConstructor
public class QuestionDTO {
    private Long id;
    private final String question;
    private final List<AnswerDTO> answers;
}
