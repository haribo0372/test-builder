package com.mzfk.test.builder.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Builder
@Getter
@Setter
@Entity(name = "answer")
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "answer_id")
    private Long id;

    @Column(name = "answerText")
    @NotBlank(message = "Текст ответа не должен быть пустым")
    private String answerText;

    @OneToMany(mappedBy = "answer", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<QuestionAnswer> questionAnswers;

    public void addQuestionAnswer(QuestionAnswer questionAnswer){
        questionAnswers.add(questionAnswer);
    }

    public Answer() {
    }

    public Answer(Long id, String answerText, Set<QuestionAnswer> questionAnswers) {
        this.id = id;
        this.answerText = answerText;
        this.questionAnswers = questionAnswers == null ? new HashSet<>() : questionAnswers;
    }

    @Override
    public String toString() {
        return "Answer{" +
                "id=" + id +
                ", answerText='" + answerText + '\'' +
                ", questionAnswers=" + questionAnswers+
                '}';
    }
}
