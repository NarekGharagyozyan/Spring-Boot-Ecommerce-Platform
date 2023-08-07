package com.smartCode.ecommerce.repository.token;

import com.smartCode.ecommerce.model.entity.AccessTokenEntity;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccessTokenRepository extends JpaRepository<AccessTokenEntity, Integer> {

    @Cacheable(cacheNames = "token")
    boolean existsByToken(String token);

    boolean existsByUserId(Integer userId);

    AccessTokenEntity findByUserId(Integer userId);

    void deleteByUserId(Integer id);
}