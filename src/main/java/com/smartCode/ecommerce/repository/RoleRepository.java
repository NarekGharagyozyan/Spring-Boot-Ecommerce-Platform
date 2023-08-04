package com.smartCode.ecommerce.repository;

import com.smartCode.ecommerce.model.entity.role.RoleEntity;
import com.smartCode.ecommerce.util.constants.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<RoleEntity, Integer> {
    boolean existsByRole(Role role);

    RoleEntity findByRole(Role role);
}