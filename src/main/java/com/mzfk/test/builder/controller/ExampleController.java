package com.mzfk.test.builder.controller;

import com.mzfk.test.builder.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/example")
@RequiredArgsConstructor
@Tag(name = "Аутентификация")
public class ExampleController {
    private final UserService service;

    @GetMapping
    @Operation(
            description = "Доступен только авторизованным пользователям, возвращает строку в случае успеха",
            summary = "Доступен только авторизованным пользователям"
    )
    public String example() {
        return "Hello, world!";
    }
}
