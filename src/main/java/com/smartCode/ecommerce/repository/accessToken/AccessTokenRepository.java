package com.smartCode.ecommerce.repository.accessToken;

import com.smartCode.ecommerce.model.entity.token.AccessTokenEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccessTokenRepository extends JpaRepository<AccessTokenEntity, Integer> {

    AccessTokenEntity findTokenEntityByToken(String token);
    void deleteTokenEntityByUserIdAndToken(Integer userId, String token);
}