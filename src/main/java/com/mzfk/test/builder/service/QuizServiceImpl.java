package com.mzfk.test.builder.service;

import com.mzfk.test.builder.model.Quiz;
import com.mzfk.test.builder.service.base.BaseInDbService;
import com.mzfk.test.builder.service.base.quiz.QuizService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Slf4j
@Service
public class QuizServiceImpl extends BaseInDbService<Quiz> implements QuizService {

    public QuizServiceImpl(JpaRepository<Quiz, Long> repository) {
        super(repository, "Quiz");
    }

    @Override
    public Quiz findById(Long id) {
        return super.findById(id);
    }

    @Override
    public Collection<Quiz> findAll() {
        return super.findAll();
    }

    @Override
    public Quiz create(Quiz quiz) {
        return quiz;
    }

    @Override
    public Quiz update(Quiz quiz) {
        Quiz storageQuiz = findById(quiz.getId());
        storageQuiz.setTitle(quiz.getTitle());
        return storageQuiz;
    }
}
