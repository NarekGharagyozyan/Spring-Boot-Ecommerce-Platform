package com.smartCode.ecommerce.service.user.impl;

import com.smartCode.ecommerce.model.dto.user.UserUpdateDto;
import com.smartCode.ecommerce.model.dto.user.filterAndSearch.FilterSearchUser;
import com.smartCode.ecommerce.model.entity.user.UserEntity;
import com.smartCode.ecommerce.repository.UserRepository;
import com.smartCode.ecommerce.service.user.UserService;
import com.smartCode.ecommerce.service.email.EmailService;
import com.smartCode.ecommerce.util.codeGenerator.RandomGenerator;
import com.smartCode.ecommerce.util.encoder.AESManager;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.nonNull;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final EmailService emailService;

    @Override
    @Transactional
    public UserEntity register(UserEntity user) {
        String generatedCode = RandomGenerator.generateNumericString(6);
        user.setCode(generatedCode);
        user.setPassword(AESManager.encrypt(user.getPassword()));
        UserEntity save = userRepository.save(user);
        emailService.sendSimpleMessage(user.getEmail(), "ecommerce verification code", "Your verification code is: " + generatedCode);
        return save;
    }

    @Override
    @Transactional
    public UserEntity login(String username, String password) {
        UserEntity byUsername = userRepository.findByUsername(username);
        if (nonNull(byUsername)){
            if (byUsername.getIsVerified()) {
                if (AESManager.decrypt(byUsername.getPassword()).equals(password))
                    return byUsername;
            } else
                throw new RuntimeException("you are not validated");
        }
        throw new RuntimeException("User not found");
    }

    @Override
    @Transactional
    public UserEntity getById(Integer id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public Boolean delete(Integer id) {
        userRepository.delete(userRepository.findById(id).get());
        return true;
    }

    @Override
    @Transactional
    public List<UserEntity> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    @Transactional
    public Boolean changePassword(String email, String password, String newPassword, String newRepeatPassword) {
        if (newPassword.equals(newRepeatPassword)) {
            UserEntity user = userRepository.findByEmail(email);
            if (nonNull(user)) {
                if ((AESManager.decrypt(user.getPassword())).equals(password)) {
                    user.setPassword(AESManager.encrypt(newPassword));
                    userRepository.save(user);
                    return true;
                }
                throw new RuntimeException("wrong password");
            }
            throw new RuntimeException("user not found");
        }
        throw new RuntimeException("passwords not matches");
    }

    @Override
    @Transactional
    public UserEntity update(Integer id, UserUpdateDto userUpdateDto) {
        UserEntity userEntity = userRepository.findById(id).get();

        userEntity.setEmail(nonNull(userUpdateDto.getEmail()) ? userUpdateDto.getEmail() : userEntity.getEmail());
        userEntity.setPhone(nonNull(userUpdateDto.getPhone()) ? userUpdateDto.getPhone() : userEntity.getPhone());
        userEntity.setUsername(nonNull(userUpdateDto.getUsername()) ? userUpdateDto.getUsername() : userEntity.getUsername());
        userEntity.setPassword(nonNull(userUpdateDto.getPassword()) ?
                AESManager.encrypt(userUpdateDto.getPassword()) :
                AESManager.encrypt(userEntity.getPassword()));

        return userEntity;
    }

    @Override
    @Transactional
    public Boolean verify(String email, String code) {
        UserEntity byEmail = userRepository.findByEmail(email);
        if (byEmail == null) {
            throw new RuntimeException("user not found");
        }

        if (!byEmail.getCode().equals(code)) {
            throw new RuntimeException("incorrect code");
        }
        byEmail.setIsVerified(true);
        userRepository.save(byEmail);
        return true;
    }

    @Override
    @Transactional
    public List<UserEntity> filter(FilterSearchUser.Filter userFilter) {
        Specification<UserEntity> specification = Specification.where((root, criteriaQuery, criteriaBuilder) -> {
            var predicates = new ArrayList<Predicate>();

            if (nonNull(userFilter.getIsVerified())) {
                Predicate isVerified = criteriaBuilder.equal(root.get("isVerified"), userFilter.getIsVerified());
                predicates.add(isVerified);
            }
            if (nonNull(userFilter.getGender())) {
                Predicate gender = criteriaBuilder.equal(root.get("gender"), userFilter.getGender());
                predicates.add(gender);
            }
            if (nonNull(userFilter.getStartAge())) {
                Predicate startAge = criteriaBuilder.greaterThan(root.get("age"), userFilter.getStartAge());
                predicates.add(startAge);
            }
            if (nonNull(userFilter.getEndAge())) {
                Predicate endAge = criteriaBuilder.lessThan(root.get("age"), userFilter.getEndAge());
                predicates.add(endAge);
            }
            /*if (nonNull(userFilter.getProductName())) {
                Join<UserEntity, ProductEntity> products = root.join("productEntities");
                Predicate productName = criteriaBuilder.equal(products.get("name"), userFilter.getProductName());
                predicates.add(productName);
            }*/
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        });
        return userRepository.findAll(specification);
    }

    @Override
    @Transactional
    public List<UserEntity> search(FilterSearchUser.Search userSearch) {
        Specification<UserEntity> specification = Specification.where((root, criteriaQuery, criteriaBuilder) -> {
            var predicates = new ArrayList<Predicate>();

            if (nonNull(userSearch.getText())) {
                Predicate nameLike = criteriaBuilder.like(root.get("name"), "%" + userSearch.getText() + "%");
                predicates.add(nameLike);

                Predicate lastNameLike = criteriaBuilder.like(root.get("lastName"), "%" + userSearch.getText() + "%");
                predicates.add(lastNameLike);

                Predicate emailLike = criteriaBuilder.like(root.get("email"), "%" + userSearch.getText() + "%");
                predicates.add(emailLike);
            }
            return criteriaBuilder.or(predicates.toArray(new Predicate[0]));
        });
        return userRepository.findAll(specification);
    }
}