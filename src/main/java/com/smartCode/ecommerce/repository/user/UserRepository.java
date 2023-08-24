package com.smartCode.ecommerce.repository.user;

import com.smartCode.ecommerce.model.entity.user.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer>, JpaSpecificationExecutor<UserEntity> {

    UserEntity findByEmail(String email);

    UserEntity findByUsername(String username);

    UserEntity findByPhone(String phone);

    UserEntity findByUsernameOrEmailOrPhone(String username, String email, String phone);
}