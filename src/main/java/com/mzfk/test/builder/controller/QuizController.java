package com.mzfk.test.builder.controller;

import com.mzfk.test.builder.dto.answer.AnswerMapper;
import com.mzfk.test.builder.dto.answer.RequestCreateAnswerDto;
import com.mzfk.test.builder.dto.answer.RequestUpdateAnswerDto;
import com.mzfk.test.builder.dto.question.QuestionMapper;
import com.mzfk.test.builder.dto.question.RequestCreateQuestionDto;
import com.mzfk.test.builder.dto.question.RequestUpdateQuestionDto;
import com.mzfk.test.builder.dto.question.ResponseQuestionDto;
import com.mzfk.test.builder.dto.quiz.QuizMapper;
import com.mzfk.test.builder.dto.quiz.RequestCreateQuiz;
import com.mzfk.test.builder.dto.quiz.RequestUpdateQuiz;
import com.mzfk.test.builder.dto.quiz.ResponseQuizDto;
import com.mzfk.test.builder.model.Answer;
import com.mzfk.test.builder.model.Question;
import com.mzfk.test.builder.model.Quiz;
import com.mzfk.test.builder.service.base.user.UserQuizService;
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
import java.util.Set;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/quiz")
@Tag(name = "Манипуляции с данными квиза. Доступно только авторизированным пользователям")
public class QuizController {
    private final UserQuizService userQuizService;

    @GetMapping
    @Operation(summary = "Получение всех квизов пользователя", method = "GET")
    public ResponseEntity<Collection<ResponseQuizDto>> findAll() {
        log.debug("GET /quiz for getting all quizzes started");
        Set<ResponseQuizDto> response = QuizMapper.toDto(userQuizService.getAllQuizzes());
        log.debug("GET /quiz for getting all quizzes ended");
        return ResponseEntity.ok(response);
    }

    @PostMapping
    @Operation(
            summary = "Создание нового квиза",
            method = "POST",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    required = true,
                    content = @Content(
                            schema = @Schema(implementation = RequestCreateQuiz.class),
                            mediaType = "application/json"
                    )
            )
    )
    public ResponseEntity<ResponseQuizDto> create(@RequestBody @Valid RequestCreateQuiz requestCreateQuiz) {
        log.debug("POST /quiz create quiz with request body = {} started", requestCreateQuiz);
        Quiz quiz = QuizMapper.fromDto(requestCreateQuiz);
        ResponseQuizDto response = QuizMapper.toDto(userQuizService.saveQuiz(quiz));
        log.debug("POST /quiz create quiz with request body = {} ended", requestCreateQuiz);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping
    @Operation(
            summary = "Изменение квиза",
            description = "Изменить у квиза можно только поле title, вопросы и варианты ответа изменяются отдельно по другим маппингам",
            method = "PUT",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    required = true,
                    content = @Content(
                            schema = @Schema(implementation = RequestUpdateQuiz.class),
                            mediaType = "application/json"
                    )
            )
    )
    public ResponseEntity<ResponseQuizDto> update(@RequestBody @Valid RequestUpdateQuiz requestUpdateQuiz) {
        log.debug("PUT /quiz create quiz with request body = {} started", requestUpdateQuiz);
        Quiz quiz = QuizMapper.fromDto(requestUpdateQuiz);
        ResponseQuizDto response = QuizMapper.toDto(userQuizService.updateQuiz(quiz));
        log.debug("PUT /quiz create quiz with request body = {} ended", requestUpdateQuiz);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/{quizId}")
    @Operation(
            summary = "Получение квиза по id",
            description = "Получение квиза по id=${quizId}",
            method = "GET"
    )
    public ResponseEntity<ResponseQuizDto> findById(@PathVariable Long quizId) {
        log.debug("GET /quiz/{} get quiz by id started", quizId);
        Quiz foundQuiz = userQuizService.getQuizById(quizId);
        ResponseQuizDto response = QuizMapper.toDto(foundQuiz);
        log.debug("GET /quiz/{} get quiz by id ended", quizId);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{quizId}")
    @Operation(
            description = "Удаление квиза с id=${quizId}",
            summary = "Удаление квиза по id, вместе с квизом удалятся все его вопросы и варианты ответов",
            method = "DELETE"
    )
    public ResponseEntity<Void> deleteById(@PathVariable Long quizId) {
        log.debug("DELETE /quiz/{} delete quiz by id started", quizId);
        userQuizService.deleteQuizById(quizId);
        log.debug("DELETE /quiz/{} delete quiz by id ended", quizId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/{quizId}")
    @Operation(
            summary = "Добавление вопроса в квиз",
            description = "Добавление вопроса в квиз с id=${quizId}",
            method = "POST",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    required = true,
                    content = @Content(
                            schema = @Schema(implementation = RequestCreateQuestionDto.class),
                            mediaType = "application/json"
                    )
            )
    )
    public ResponseEntity<ResponseQuizDto> addQuestionToQuiz(@PathVariable Long quizId,
                                                             @RequestBody @Valid RequestCreateQuestionDto requestQuestion) {
        log.debug("POST /quiz/{} add {} to Quiz{ id = {} } started", quizId, requestQuestion, quizId);
        Question question = QuestionMapper.fromDto(requestQuestion);
        ResponseQuizDto response = QuizMapper.toDto(userQuizService.addQuestionToQuiz(quizId, question));
        log.debug("POST /quiz/{} add {} to Quiz{ id = {} } ended", quizId, requestQuestion, quizId);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("{quizId}")
    @Operation(
            summary = "Изменение вопроса в квизе",
            description = "Изменение вопроса в квизе с id=${quizId}." +
                    " У вопроса изменить можно только поле 'questionText'",
            method = "PUT",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    required = true,
                    content = @Content(
                            schema = @Schema(implementation = RequestUpdateQuestionDto.class),
                            mediaType = "application/json"
                    )
            )
    )
    public ResponseEntity<ResponseQuizDto> updateQuizQuestion(@PathVariable Long quizId,
                                                              @RequestBody @Valid RequestUpdateQuestionDto requestQuestion) {
        log.debug("PUT /quiz/{} update {} of Quiz{ id = {} } started", quizId, requestQuestion, quizId);
        Question question = QuestionMapper.fromDto(requestQuestion);
        ResponseQuizDto response = QuizMapper.toDto(userQuizService.updateQuestion(quizId, question));
        log.debug("PUT /quiz/{} update {} of Quiz{ id = {} } ended", quizId, requestQuestion, quizId);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("{quizId}/{questionId}")
    @Operation(
            description = "Получение вопроса по id=${questionId}, который принадлежит квизу с id=${quizId}",
            summary = "Получение вопроса по id",
            method = "GET"
    )
    public ResponseEntity<ResponseQuestionDto> getQuestion(@PathVariable Long quizId,
                                                           @PathVariable Long questionId) {
        Question question = userQuizService.getQuestionById(quizId, questionId);
        return ResponseEntity.ok(QuestionMapper.toDto(question));
    }

    @DeleteMapping("{quizId}/{questionId}")
    @Operation(
            description = "Удаление вопроса по id=${questionId}, который принадлежит квизу с id=${quizId}",
            summary = "Удаление вопроса",
            method = "DELETE"
    )
    public ResponseEntity<Void> deleteQuestion(@PathVariable Long quizId,
                                               @PathVariable Long questionId) {
        userQuizService.deleteQuestionById(quizId, questionId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
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
                            schema = @Schema(implementation = RequestCreateAnswerDto.class),
                            mediaType = "application/json"
                    )
            )
    )
    public ResponseEntity<ResponseQuizDto> addAnswerToQuestion(@PathVariable Long quizId,
                                                               @PathVariable Long questionId,
                                                               @RequestBody @Valid RequestCreateAnswerDto requestCreateAnswerDto) {
        Answer answer = AnswerMapper.fromDto(requestCreateAnswerDto);
        ResponseQuizDto response = QuizMapper.toDto(userQuizService.addAnswerToQuestion(quizId, questionId, answer));
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("{quizId}/{questionId}")
    @Operation(
            description = "Изменение варианта ответа в вопросе с id=${questionId}," +
                    " который принадлежит квизу с id=${quizId}. " +
                    "У варианта ответа можно изменить только текст и характер верности",
            summary = "Добавление варианта ответа в вопрос",
            method = "PUT",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    required = true,
                    content = @Content(
                            schema = @Schema(implementation = RequestUpdateAnswerDto.class),
                            mediaType = "application/json"
                    )
            )
    )
    public ResponseEntity<ResponseQuizDto> updateAnswerOfQuestion(@PathVariable Long quizId,
                                                                  @PathVariable Long questionId,
                                                                  @RequestBody @Valid RequestUpdateAnswerDto requestUpdateAnswerDto) {
        Answer answer = AnswerMapper.fromDto(requestUpdateAnswerDto);
        ResponseQuizDto response = QuizMapper.toDto(userQuizService.updateAnswer(quizId, questionId, answer));
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @DeleteMapping("{quizId}/{questionId}/{answerId}")
    @Operation(
            description = "Удаление варианта ответа по id=${answerId}, который принадлежит вопросу с id=${questionId}",
            summary = "Удаление варианта ответа",
            method = "DELETE"
    )
    public ResponseEntity<Void> deleteAnswer(@PathVariable Long quizId,
                                             @PathVariable Long questionId, @PathVariable Long answerId) {
        userQuizService.deleteAnswerById(quizId, questionId, answerId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
