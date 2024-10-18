package com.mzfk.test.builder.dto.quiz;

import com.mzfk.test.builder.dto.quiz.simply.QuestionDTO;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@RequiredArgsConstructor
public class QuizResponse {
    private final Long id;
    private final String title;
    private final List<QuestionDTO> questions;
}
