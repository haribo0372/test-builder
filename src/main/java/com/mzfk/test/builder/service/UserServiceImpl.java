package com.mzfk.test.builder.service;

import com.mzfk.test.builder.model.User;
import com.mzfk.test.builder.repository.UserRepository;
import com.mzfk.test.builder.service.base.user.UserService;
import com.mzfk.test.builder.util.exception.NotFoundException;
import com.mzfk.test.builder.util.exception.ValidateException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository repository;

    @Override
    public User save(User user) {
        return repository.save(user);
    }

    @Override
    public User create(User user) {
        if (repository.existsByUsername(user.getUsername())) {
            log.info("Пользователь не прошел проверку на уникальность поля username '{}'", user.getUsername());
            throw new ValidateException("Пользователь с таким именем уже существует");
        }

        if (repository.existsByEmail(user.getEmail())) {
            log.info("Пользователь не прошел проверку на уникальность поля email '{}'", user.getEmail());
            throw new ValidateException("Пользователь с таким email уже существует");
        }

        User savedUser = save(user);
        log.info("Пользователь '{}' сохранен", savedUser.getUsername());
        return savedUser;
    }

    @Override
    public User getByUsername(String username) {
        User foundUser = repository.findByUsername(username)
                .orElseThrow(() -> {
                    log.info("Не удалось найти пользователя по username='{}'", username);
                    return new NotFoundException("Пользователь не найден");
                });

        log.info("Успешно найден пользователь по username='{}'", username);
        return foundUser;
    }

    @Override
    public UserDetailsService userDetailsService() {
        return this::getByUsername;
    }

    @Override
    public User getCurrentUser() {
        var username = SecurityContextHolder.getContext().getAuthentication().getName();
        return getByUsername(username);
    }
}