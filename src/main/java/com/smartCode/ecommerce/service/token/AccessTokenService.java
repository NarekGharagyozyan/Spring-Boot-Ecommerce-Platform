package com.smartCode.ecommerce.service.token;

import com.smartCode.ecommerce.model.entity.token.AccessTokenEntity;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;

public interface AccessTokenService {

    void deleteToken(Integer userId, String token);

    void saveToken(AccessTokenEntity tokenEntity);

    AccessTokenEntity getToken(String token);
}
