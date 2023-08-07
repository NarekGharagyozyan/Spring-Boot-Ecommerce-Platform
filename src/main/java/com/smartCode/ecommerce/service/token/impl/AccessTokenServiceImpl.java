package com.smartCode.ecommerce.service.token.impl;

import com.smartCode.ecommerce.model.entity.token.AccessTokenEntity;
import com.smartCode.ecommerce.repository.AccessTokenRepository;
import com.smartCode.ecommerce.service.token.AccessTokenService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AccessTokenServiceImpl implements AccessTokenService {

    AccessTokenRepository tokenRepository;

    @Override
    @Transactional
    @CacheEvict(value = "token", allEntries = true)
    public void deleteToken(Integer userId) {
        tokenRepository.deleteTokenEntityByUserId(userId);
    }

    @Override
    @Transactional
    public void saveToken(AccessTokenEntity tokenEntity) {
        tokenRepository.save(tokenEntity);
    }

    @Override
    @Transactional
    @Cacheable(value = "token")
    public AccessTokenEntity getToken(String token) {
        return tokenRepository.findTokenEntityByToken(token);
    }
}
