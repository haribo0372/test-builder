package com.mzfk.test.builder.service;

import com.mzfk.test.builder.model.Answer;
import com.mzfk.test.builder.model.QuestionAnswer;
import com.mzfk.test.builder.repository.AnswerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AnswerService {
    private final AnswerRepository answerRepository;

//    public Answer save(final Answer answer) {
//        String answerText = answer.getAnswerText();
//        return findByAnswerText(answerText)
//                .orElseGet(() -> {
//                    Answer newAnswer = Answer.builder()
//                            .answerText(answerText)
//                            .build();
//                    return answerRepository.save(newAnswer);
//                });
//    }

//    public void save(final QuestionAnswer questionAnswer) {
//        Answer answer = save(questionAnswer.getAnswer());
//        answer.addQuestionAnswer(questionAnswer);
//        questionAnswer.setAnswer(answer);
//
//        save(answer);
//    }

    public Answer save(Answer answer) {
        Answer answer1;
        try {
            answer1 = answerRepository.save(answer);
        } catch (Exception e) {
            throw new RuntimeException(answer.toString() + " : " + e.getMessage());
        }

        return answer1;
    }

    public Optional<Answer> findById(final Long id) {
        return answerRepository.findById(id);
    }

    public Optional<Answer> findByAnswerText(String answerText) {
        return answerRepository.findByAnswerText(answerText);
    }
}
