package com.mzfk.test.builder.controller;

import com.mzfk.test.builder.model.Answer;
import com.mzfk.test.builder.model.Question;
import com.mzfk.test.builder.model.Quiz;
import com.mzfk.test.builder.service.UserQuizService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/quiz")
@Tag(name = "Манипуляции с данными квиза. Доступно только авторизированным пользователям")
public class QuizController {
    private final UserQuizService userQuizService;

    @GetMapping
    @Operation(summary = "Получение всех квизов пользователя", method = "GET")
    public ResponseEntity<Collection<Quiz>> findAll() {
        return ResponseEntity.ok(userQuizService.getAllQuizzes());
    }

    @PostMapping
    @Operation(
            summary = "Создание нового квиза",
            method = "POST",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    required = true,
                    content = @Content(
                            schema = @Schema(implementation = Quiz.class),
                            mediaType = "application/json"
                    )
            )
    )
    public ResponseEntity<Quiz> create(@RequestBody @Valid Quiz quiz) {
        System.out.println(quiz);
        return new ResponseEntity<>(userQuizService.saveQuiz(quiz), HttpStatus.CREATED);
    }

    @PutMapping
    @Operation(
            summary = "Изменение квиза",
            method = "PUT",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    required = true,
                    content = @Content(
                            schema = @Schema(implementation = Quiz.class),
                            mediaType = "application/json"
                    )
            )
    )
    public ResponseEntity<Quiz> update(@RequestBody @Valid Quiz quiz) {
        return ResponseEntity.ok(userQuizService.saveQuiz(quiz));
    }

    @GetMapping("/{quizId}")
    @Operation(
            description = "Получение квиза по id=${quizId}",
            summary = "Получение квиза по id",
            method = "GET"
    )
    public ResponseEntity<Quiz> findById(@PathVariable Long quizId) {
        return ResponseEntity.ok(userQuizService.getQuizById(quizId));
    }

    @DeleteMapping("/{quizId}")
    @Operation(
            description = "Удаление квиза с id=${quizId}",
            summary = "Удаление квиза по id",
            method = "DELETE"
    )
    public ResponseEntity<Void> deleteById(@PathVariable Long quizId) {
        userQuizService.deleteQuizById(quizId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/{quizId}")
    @Operation(
            description = "Добавление вопроса в квиз с id=${quizId}",
            summary = "Добавление вопроса в квиз",
            method = "POST",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    required = true,
                    content = @Content(
                            schema = @Schema(implementation = Question.class),
                            mediaType = "application/json"
                    )
            )
    )
    public ResponseEntity<Quiz> addQuestionToQuiz(@PathVariable Long quizId,
                                                  @RequestBody @Valid Question question) {
        return ResponseEntity.ok(
                userQuizService.addQuestionToQuiz(quizId, question));
    }

    @PutMapping("{quizId}")
    @Operation(
            description = "Обновление вопроса в квизе с id=${quizId}," +
                    " если вопрос не принадлежит данному квизу, генерируется ошибка",
            summary = "Обновление вопроса в квизе",
            method = "PUT",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    required = true,
                    content = @Content(
                            schema = @Schema(implementation = Question.class),
                            mediaType = "application/json"
                    )
            )
    )
    public ResponseEntity<Quiz> updateQuizQuestion(@PathVariable Long quizId,
                                                   @RequestBody @Valid Question question) {
        return ResponseEntity.ok(userQuizService.updateQuizQuestion(quizId, question));
    }

    @GetMapping("{quizId}/{questionId}")
    @Operation(
            description = "Получение вопроса по id=${questionId}, который принадлежит квизус id=${quizId}",
            summary = "Получение вопроса по id",
            method = "GET"
    )
    public ResponseEntity<Question> getQuestion(@PathVariable Long quizId,
                                                @PathVariable Long questionId) {
        return ResponseEntity.ok(userQuizService.getQuestionById(quizId, questionId));
    }

    @PostMapping("{quizId}/{questionId}")
    @Operation(
            description = "Добавление варианта ответа в вопрос с id=${questionId}," +
                    " который принадлежит квизу с id=${quizId}",
            summary = "Добавление варианта ответа в вопрос",
            method = "POST",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    required = true,
                    content = @Content(
                            schema = @Schema(implementation = Answer.class),
                            mediaType = "application/json"
                    )
            )
    )
    public ResponseEntity<Quiz> addAnswerToQuestion(@PathVariable Long quizId,
                                                    @PathVariable Long questionId,
                                                    @RequestBody @Valid Answer answer) {

        return ResponseEntity.ok(userQuizService.addAnswerToQuestion(quizId, questionId, answer));
    }

    @DeleteMapping("{quizId}/{questionId}")
    @Operation(
            description = "Удаление вопроса по id=${questionId}, который принадлежит квизу с id=${quizId}",
            summary = "Удаление вопроса",
            method = "DELETE"
    )
    public ResponseEntity<Void> deleteQuestion(@PathVariable Long quizId,
                                               @PathVariable Long questionId) {
        userQuizService.deleteQuizQuestionById(quizId, questionId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
