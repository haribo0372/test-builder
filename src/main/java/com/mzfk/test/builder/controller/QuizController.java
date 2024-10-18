package com.mzfk.test.builder.controller;

import com.mzfk.test.builder.dto.quiz.QuizRequest;
import com.mzfk.test.builder.dto.quiz.QuizResponse;
import com.mzfk.test.builder.dto.quiz.mapper.QuizMapper;
import com.mzfk.test.builder.model.Quiz;
import com.mzfk.test.builder.service.UserQuizService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/quiz")
public class QuizController {
    private final UserQuizService userQuizService;
    private final QuizMapper mapper;

    @GetMapping
    public ResponseEntity<Collection<QuizResponse>> findAll(){
        Collection<QuizResponse> collection = userQuizService
                .getAllQuizzes().stream().map(mapper::toDto).collect(Collectors.toSet());

        return ResponseEntity.ok(collection);
    }

    @PostMapping
    public ResponseEntity<QuizResponse> createNewQuiz(@RequestBody @Valid QuizRequest quizRequest) {

        Quiz quiz = mapper.toQuiz(quizRequest);
        Quiz savedQuiz = userQuizService.saveQuiz(quiz);

        return ResponseEntity.ok(mapper.toDto(savedQuiz));
    }

//    @PutMapping("/{userId}")
//    public ResponseEntity updateQuiz(
//            @PathVariable Long userId,
//            @RequestBody @Valid QuizResponse quizResponse) {
//
////        userQuizService.updateQuiz(userId, mapper.toQuiz(quizResponse));
//        return new ResponseEntity(HttpStatus.BAD_REQUEST);
//    }

    @GetMapping("/{quizId}")
    public ResponseEntity<QuizResponse> findById(
                                                 @PathVariable Long quizId) {
        Quiz foundQuiz = userQuizService.getQuizById(quizId);

        return ResponseEntity.ok(mapper.toDto(foundQuiz));
    }
}
