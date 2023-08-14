package com.smartCode.ecommerce.configuration;

import com.smartCode.ecommerce.model.entity.user.UserEntity;
import com.smartCode.ecommerce.repository.RoleRepository;
import com.smartCode.ecommerce.repository.UserRepository;
import com.smartCode.ecommerce.util.constants.Gender;
import com.smartCode.ecommerce.util.constants.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.time.LocalDate;

@Configuration
@RequiredArgsConstructor
public class InitConfig {

    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    @PostConstruct
    @Transactional
    public void setupBb() {
        createAdmin();
    }

    private void createAdmin() {
        if (userRepository.findByUsername("admin") == null) {
            UserEntity admin = new UserEntity();
            admin.setPhone("+37400000000");
            admin.setUsername("admin");
            admin.setEmail("admin@gmail.com");
            admin.setAge(20);
            admin.setGender(Gender.MALE);
            admin.setDate(LocalDate.now());
            admin.setName("Narek");
            admin.setLastName("Gharagyozyan");
            admin.setPassword(passwordEncoder.encode("narek2003"));
            admin.setRole(roleRepository.findByRole(Role.ROLE_ADMIN));
            userRepository.save(admin);
        }
    }
}
