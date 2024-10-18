package com.mzfk.test.builder.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "question_answer")
public class QuestionAnswer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "question_id")
    private Question question;

    @ManyToOne
    @JoinColumn(name = "answer_id")
    private Answer answer;

    @Column(name = "is_correct")
    @NotNull(message = "Характер верности ответа должен быть определен (true - ответ верный; false - ответ неверный)")
    private boolean isCorrect;

    @Override
    public String toString() {
        return "QuestionAnswer{" +
                "id=" + id +
                ", question=" + question.getId() +
                ", answer=" + answer.getId() +
                ", isCorrect=" + isCorrect +
                '}';
    }
}
