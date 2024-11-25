package com.mzfk.test.builder.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@Entity(name = "question")
public class Question extends BaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @Column(name = "question", nullable = false)
    private String questionText;

    @ManyToOne
    @JoinColumn(name = "quiz_id", nullable = false)
    private Quiz quiz;

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Answer> answers;

    public Question() {
        this.answers = new HashSet<>();
    }

    public Question(Long id, String questionText, Set<Answer> answers) {
        this.id = id;
        this.questionText = questionText;
        this.answers =
                answers == null ? new HashSet<>() : answers;
    }

    public Question(Long id, String questionText) {
        this.id = id;
        this.questionText = questionText;
        this.answers = new HashSet<>();
    }

    public void addAnswer(Answer answer) {
        answer.setQuestion(this);
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
        return super.equals(o) && Objects.equals(questionText, question.questionText);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, questionText);
    }
}
