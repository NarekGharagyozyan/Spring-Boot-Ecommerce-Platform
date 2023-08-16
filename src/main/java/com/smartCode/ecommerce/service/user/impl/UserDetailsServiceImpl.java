package com.smartCode.ecommerce.service.user.impl;

import com.smartCode.ecommerce.model.dto.UserDetailsImpl;
import com.smartCode.ecommerce.model.entity.user.UserEntity;
import com.smartCode.ecommerce.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    @Transactional(readOnly = true)
    public UserDetailsImpl loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findByUsernameOrEmailOrPhone(username, username, username);
        return UserDetailsImpl.build(userEntity.getId(),
                                userEntity.getUsername(),
                                userEntity.getRole().getRole().getName());
    }

}

