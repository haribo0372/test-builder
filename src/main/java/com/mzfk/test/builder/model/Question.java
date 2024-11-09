package com.mzfk.test.builder.model;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Builder
@Getter
@Setter
@Entity(name = "question")
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "question_id")
    private Long id;

    @Column(name = "question", nullable = false)
    private String questionText;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Answer> answers;

    public Question() {
        answers = new HashSet<>();
    }

    public Question(Long id, String questionText, Set<Answer> answers) {
        this.id = id;
        this.questionText = questionText;
        this.answers =
                answers == null ? new HashSet<>() : answers;
    }

    public void addAnswer(Answer answer) {
        answers.add(answer);
    }

    public void removeAnswer(Answer answer) {
        answers.remove(answer);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Question question = (Question) o;
        return Objects.equals(id, question.id) && Objects.equals(questionText, question.questionText);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, questionText);
    }
}
