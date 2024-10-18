package com.mzfk.test.builder.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.HashSet;
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

    @NotBlank(message = "Текст вопроса не должно быть пустым")
    @Column(name = "question", nullable = false)
    private String questionText;

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<QuestionAnswer> questionAnswers;

    public Question() {
        questionAnswers = new HashSet<>();
    }

    public Question(Long id, String questionText, Set<QuestionAnswer> questionAnswers) {
        this.id = id;
        this.questionText = questionText;
        this.questionAnswers =
                questionAnswers == null ? new HashSet<>() : questionAnswers;
    }

    public void addQuestionAnswer(QuestionAnswer questionAnswer) {
        questionAnswers.add(questionAnswer);
    }

    @Override
    public String toString() {
        return "Question{" +
                "id=" + id +
                ", questionText='" + questionText + '\'' +
                ", questionAnswers=" + questionAnswers +
                '}';
    }
}
