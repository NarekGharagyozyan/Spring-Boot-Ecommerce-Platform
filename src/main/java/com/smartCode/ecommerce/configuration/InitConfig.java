package com.smartCode.ecommerce.configuration;

import com.smartCode.ecommerce.model.entity.role.RoleEntity;
import com.smartCode.ecommerce.repository.RoleRepository;
import com.smartCode.ecommerce.util.constants.Role;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.Transactional;


import javax.annotation.PostConstruct;

@Configuration
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class InitConfig {

    RoleRepository roleRepository;

    @PostConstruct
    @Transactional
    public void setupBb() {
        if (!roleRepository.existsByRole(Role.ROLE_ADMIN)){
            var admin = new RoleEntity();
            admin.setRole(Role.ROLE_ADMIN);
            roleRepository.save(admin);
        }

        if (!roleRepository.existsByRole(Role.ROLE_USER)) {
            var user = new RoleEntity();
            user.setRole(Role.ROLE_USER);
            roleRepository.save(user);
        }
    }

}
