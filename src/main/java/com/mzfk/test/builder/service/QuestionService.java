package com.mzfk.test.builder.service;

import com.mzfk.test.builder.model.Answer;
import com.mzfk.test.builder.model.Question;
import com.mzfk.test.builder.model.QuestionAnswer;
import com.mzfk.test.builder.repository.QuestionAnswerRepository;
import com.mzfk.test.builder.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class QuestionService {
    private final QuestionRepository questionRepository;
    private final AnswerService answerService;
    private final QuestionAnswerRepository questionAnswerRepository;

    public Optional<Question> findById(final Long id) {
        return questionRepository.findById(id);
    }

    public Optional<Question> findByQuestionText(String questionText) {
        return questionRepository.findByQuestionText(questionText);
    }

    public void save(final Question question) {
        final Set<QuestionAnswer> questionAnswerSet = new HashSet<>(question.getQuestionAnswers());

        // Очищаем связи вопроса перед сохранением
        question.getQuestionAnswers().clear();
        Question savedQuestion = questionRepository.save(question);

        questionAnswerSet.forEach(questionAnswer -> {
            questionAnswer.setQuestion(savedQuestion);
            savedQuestion.addQuestionAnswer(questionAnswer);

            // Проверяем, существует ли Answer, и сохраняем его, если не существует
            String answerText = questionAnswer.getAnswer().getAnswerText();
            Answer answer = answerService.findByAnswerText(answerText).orElseGet(() -> Answer.builder().answerText(answerText).build());
//            Set<QuestionAnswer> questionAnswers = new HashSet<>(answer.getQuestionAnswers());

            System.out.println(answer);
//            answer.getQuestionAnswers().clear();
            answer.addQuestionAnswer(questionAnswer);

            answerService.save(answer);
            // Устанавливаем Answer для QuestionAnswer
            questionAnswer.setAnswer(answer);

            // Сохраняем QuestionAnswer
            questionAnswerRepository.save(questionAnswer);
        });

        // Сохраняем Question с обновленными связями
        questionRepository.save(savedQuestion);
    }

}
