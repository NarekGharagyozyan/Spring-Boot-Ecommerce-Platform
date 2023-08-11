package com.smartCode.ecommerce.configuration;

import com.smartCode.ecommerce.model.entity.role.RoleEntity;
import com.smartCode.ecommerce.model.entity.user.UserEntity;
import com.smartCode.ecommerce.repository.RoleRepository;
import com.smartCode.ecommerce.repository.UserRepository;
import com.smartCode.ecommerce.util.constants.Gender;
import com.smartCode.ecommerce.util.constants.Role;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;


import javax.annotation.PostConstruct;
import java.time.LocalDate;

@Configuration
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class InitConfig {

    RoleRepository roleRepository;
    PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    @PostConstruct
    @Transactional
    public void setupBb() {
//        createRoles();
        createAdmin();
    }

    private void createAdmin() {
        if (userRepository.findByUsername("admin") == null){
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

//    private void createRoles() {
//        if (!roleRepository.existsByRole(Role.ROLE_ADMIN)){
//            var admin = new RoleEntity();
//            admin.setRole(Role.ROLE_ADMIN);
//            roleRepository.save(admin);
//        }
//
//        if (!roleRepository.existsByRole(Role.ROLE_USER)) {
//            var user = new RoleEntity();
//            user.setRole(Role.ROLE_USER);
//            roleRepository.save(user);
//        }
//    }

}
