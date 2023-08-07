package com.smartCode.ecommerce.service.token;

import com.smartCode.ecommerce.model.entity.token.AccessTokenEntity;

import javax.persistence.criteria.CriteriaBuilder;

public interface AccessTokenService {

    void saveToken(AccessTokenEntity tokenEntity);

    void deleteToken(Integer userId);

    AccessTokenEntity getToken(String token);
}
