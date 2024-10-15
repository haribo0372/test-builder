package com.mzfk.testBuilder.repository;

import com.mzfk.testBuilder.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
