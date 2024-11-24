package com.mzfk.test.builder.service.base;

import com.mzfk.test.builder.model.BaseModel;
import com.mzfk.test.builder.util.exception.NotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;

import static java.lang.String.format;

@Slf4j
@AllArgsConstructor
public abstract class BaseInDbService<T extends BaseModel> extends BaseService<T, Long> {
    protected final JpaRepository<T, Long> repository;
    private final String nameModelForLog;

    @Override
    protected T findById(Long id) {
        return repository.findById(id).orElseThrow(() -> {
            String message = format("%s с id=%d не найден", nameModelForLog, id);
            log.warn(message);
            return new NotFoundException(message);
        });
    }

    @Override
    protected Collection<T> findAll() {
        List<T> foundEntities = repository.findAll();
        log.info("Все {} успешно загружены из бд", nameModelForLog);
        return foundEntities;
    }

    @Override
    @Transactional
    protected T save(T entity) {
        T savedEntity = repository.save(entity);
        log.info("{}{id={}} успешно сохранен", nameModelForLog, savedEntity.getId());
        return savedEntity;
    }

    @Override
    @Transactional
    protected void deleteById(Long id) {
        repository.delete(this.findById(id));
        log.info("{}{id={}} успешно удален", nameModelForLog, id);
    }
}
