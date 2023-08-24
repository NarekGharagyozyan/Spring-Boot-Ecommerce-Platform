package com.smartCode.ecommerce.repository.role;

import com.smartCode.ecommerce.model.entity.role.RoleEntity;
import com.smartCode.ecommerce.util.constants.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, Integer> {
    boolean existsByRole(Role role);

    RoleEntity findByRole(Role role);
}