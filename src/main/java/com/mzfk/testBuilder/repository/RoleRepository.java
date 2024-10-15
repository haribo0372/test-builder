package com.mzfk.testBuilder.repository;

import com.mzfk.testBuilder.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
