package com.smartCode.ecommerce.repository;

import com.smartCode.ecommerce.model.entity.token.AccessTokenEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccessTokenRepository extends JpaRepository<AccessTokenEntity, Integer> {

    AccessTokenEntity findTokenEntityByToken(String token);

    void deleteTokenEntityByUserId(Integer userId);

    void deleteTokenEntityByUserIdAndToken(Integer userId, String token);
}