package com.mzfk.test.builder.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "quiz")
public class Quiz {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "quiz_id")
    private Long id;

    @Column(name = "title")
    @NotBlank(message = "Название теста не должно быть пустым")
    private String title;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private final Set<Question> questions = new HashSet<>();

    public void addQuestion(Question question){
        questions.add(question);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Quiz quiz = (Quiz) o;
        return Objects.equals(id, quiz.id) && Objects.equals(title, quiz.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title);
    }
}
