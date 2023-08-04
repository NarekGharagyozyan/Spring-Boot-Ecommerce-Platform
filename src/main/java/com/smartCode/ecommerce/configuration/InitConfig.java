package com.smartCode.ecommerce.configuration;

import com.smartCode.ecommerce.model.entity.role.RoleEntity;
import com.smartCode.ecommerce.repository.role.RoleRepository;
import com.smartCode.ecommerce.util.constants.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.Transactional;
import com.smartCode.ecommerce.repository.RoleRepository;
import com.smartCode.ecommerce.util.constants.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Configuration
@RequiredArgsConstructor
public class InitConfig {

    private final RoleRepository roleRepository;

    @PostConstruct
    @Transactional
    public void setupDb() {

        if (!roleRepository.existsByRole(Role.ROLE_ADMIN)) {
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
