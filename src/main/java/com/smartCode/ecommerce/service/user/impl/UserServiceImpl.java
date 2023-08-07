package com.smartCode.ecommerce.service.user.impl;

import com.smartCode.ecommerce.exceptions.DuplicationException;
import com.smartCode.ecommerce.exceptions.ResourceNotFoundException;
import com.smartCode.ecommerce.exceptions.ValidationException;
import com.smartCode.ecommerce.exceptions.VerificationException;
import com.smartCode.ecommerce.mapper.UserMapper;
import com.smartCode.ecommerce.model.dto.user.UserAuthDto;
import com.smartCode.ecommerce.model.dto.user.UserRequestDto;
import com.smartCode.ecommerce.model.dto.user.UserResponseDto;
import com.smartCode.ecommerce.model.dto.user.UserUpdateDto;
import com.smartCode.ecommerce.model.dto.user.filterAndSearch.FilterSearchUser;
import com.smartCode.ecommerce.model.entity.token.AccessTokenEntity;
import com.smartCode.ecommerce.model.entity.user.UserEntity;
import com.smartCode.ecommerce.repository.RoleRepository;
import com.smartCode.ecommerce.repository.UserRepository;
import com.smartCode.ecommerce.service.email.EmailService;
import com.smartCode.ecommerce.service.token.AccessTokenService;
import com.smartCode.ecommerce.service.user.UserService;
import com.smartCode.ecommerce.util.codeGenerator.RandomGenerator;
import com.smartCode.ecommerce.util.constants.Message;
import com.smartCode.ecommerce.util.constants.Role;
import com.smartCode.ecommerce.util.constants.Root;
import com.smartCode.ecommerce.util.currentUser.CurrentUser;
import com.smartCode.ecommerce.util.jwt.JwtTokenProvider;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import javax.persistence.criteria.Predicate;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.nonNull;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserServiceImpl implements UserService {

    UserRepository userRepository;
    EmailService emailService;
    UserMapper userMapper;
    AuthenticationManager authenticationManager;
    JwtTokenProvider jwtTokenProvider;
    UserDetailsService userDetailsService;
    PasswordEncoder passwordEncoder;
    RoleRepository roleRepository;
    AccessTokenService tokenService;

    @Override
    @Transactional
    public UserResponseDto register(UserRequestDto user) {
        if (userRepository.findByUsername(user.getUsername()) != null) {
            throw new DuplicationException(Message.USER_WITH_USERNAME_ALREADY_EXISTS);
        }
        if (userRepository.findByEmail(user.getEmail()) != null) {
            throw new DuplicationException(Message.USER_WITH_EMAIL_ALREADY_EXISTS);
        }
        if (userRepository.findByPhone(user.getPhone()) != null) {
            throw new DuplicationException(Message.USER_WITH_PHONE_ALREADY_EXISTS);
        }

        UserEntity entity = userMapper.toEntity(user);
        String generatedCode = RandomGenerator.generateNumericString(6);
        entity.setCode(generatedCode);
        setUserAge(entity);
        entity.setPassword(passwordEncoder.encode(user.getPassword()));
        entity.setRole(roleRepository.findByRole(Role.ROLE_USER));
        UserEntity save = userRepository.save(entity);
        emailService.sendSimpleMessage(user.getEmail(), Message.EMAIL_SUBJECT,
                Message.EMAIL_MESSAGE + generatedCode);
        return userMapper.toResponseDto(save);
    }

    @Override
    @Transactional
    public void logout() {
        tokenService.deleteToken(CurrentUser.getId());
    }

    @Override
    @Transactional
    public UserAuthDto login(String username, String password) {
        /*UserEntity byUsername = userRepository.findByUsername(username);
        if (nonNull(byUsername)) {
            if (byUsername.getIsVerified()) {
                if (MD5Encoder.encode(password).equals(byUsername.getPassword()))
                    return userMapper.toResponseDto(byUsername);
                else
                    throw new ValidationException(Message.WRONG_USERNAME_OR_PASSWORD);
            } else
                throw new VerificationException(Message.VERIFY_ACCOUNT);
        }
        throw new ResourceNotFoundException(Message.USER_NOT_FOUND);*/

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                username, password));
        UserEntity user = (UserEntity) userDetailsService.loadUserByUsername(username);
        Integer userId = user.getId();
        String accessToken = jwtTokenProvider.generateAccessToken(userId, user.getUsername(), user.getRole().getRole().getName());

        String token = accessToken.split("\\.")[2];
        System.out.println(token);
        AccessTokenEntity tokenEntity = new AccessTokenEntity();
        tokenEntity.setToken(token);
        tokenEntity.setUser(user);
        tokenService.saveToken(tokenEntity);

        return new UserAuthDto(userId, accessToken);
    }

    @Override
    @Transactional(readOnly = true)
    public UserResponseDto getById(Integer id) {
        UserEntity userEntity = userRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException(Message.USER_NOT_FOUND));
        UserResponseDto responseDto = userMapper.toResponseDto(userEntity);

        RestTemplate restTemplate = new RestTemplate();
        var responseEntity = restTemplate.getForEntity(
                String.format("http://localhost:8081/cards/find/%d", id),
                List.class);

        var cardResponseDto = responseEntity.getBody();
        responseDto.setCards(cardResponseDto);
        return responseDto;
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserResponseDto> getAllUsers() {
        List<UserEntity> all = userRepository.findAll();
        List<UserResponseDto> responseDtos = new ArrayList<>();
        for (UserEntity userEntity : all) {
            UserResponseDto responseDto = userMapper.toResponseDto(userEntity);

            RestTemplate restTemplate = new RestTemplate();
            var responseEntity = restTemplate.getForEntity(
                    String.format("http://localhost:8081/cards/find/%d", responseDto.getId()),
                    List.class);

            var cardResponseDto = responseEntity.getBody();
            responseDto.setCards(cardResponseDto);
            responseDtos.add(responseDto);
        }
        return responseDtos;
    }

    @Async
    @Override
    @Transactional
    public void delete(Integer id) {
        UserEntity userEntity = userRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException(Message.USER_NOT_FOUND));
        userRepository.delete(userEntity);

        RestTemplate restTemplate = new RestTemplate();

        restTemplate.delete(
                String.format("http://localhost:8081/cards/delete/owner/%d", id));
    }

    @Override
    @Transactional
    public void changePassword(String password, String newPassword, String newRepeatPassword) {
        if (newPassword.equals(newRepeatPassword)) {
            UserEntity user = userRepository.findById(CurrentUser.getId()).orElseThrow(
                    () -> new ResourceNotFoundException(Message.USER_NOT_FOUND));
            if (nonNull(user)) {
                System.out.println(passwordEncoder.matches(password, user.getPassword()));
                if (passwordEncoder.matches(password, user.getPassword())) {
                    user.setPassword(passwordEncoder.encode(newPassword));
                    userRepository.save(user);
                }
                else
                    throw new ValidationException(Message.WRONG_USERNAME_OR_PASSWORD);
            }
            else
                throw new ResourceNotFoundException(Message.USER_NOT_FOUND);
        }
        else
            throw new ValidationException(Message.PASSWORDS_NOT_MATCHES);
    }

    @Override
    @Transactional
    public UserResponseDto update(Integer id, UserUpdateDto userUpdateDto) {
        UserEntity userEntity = userRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException(Message.USER_NOT_FOUND));

        if (userRepository.findByUsername(userUpdateDto.getUsername()) != null) {
            throw new DuplicationException(Message.USER_WITH_USERNAME_ALREADY_EXISTS);
        }
        if (userRepository.findByEmail(userUpdateDto.getEmail()) != null) {
            throw new DuplicationException(Message.USER_WITH_EMAIL_ALREADY_EXISTS);
        }
        if (userRepository.findByPhone(userUpdateDto.getPhone()) != null) {
            throw new DuplicationException(Message.USER_WITH_PHONE_ALREADY_EXISTS);
        }

        userEntity.setEmail(nonNull(userUpdateDto.getEmail()) ? userUpdateDto.getEmail() : userEntity.getEmail());
        userEntity.setPhone(nonNull(userUpdateDto.getPhone()) ? userUpdateDto.getPhone() : userEntity.getPhone());
        userEntity.setUsername(nonNull(userUpdateDto.getUsername()) ? userUpdateDto.getUsername() : userEntity.getUsername());
        userEntity.setPassword(nonNull(userUpdateDto.getPassword()) ?
                passwordEncoder.encode(userUpdateDto.getPassword()) :
                passwordEncoder.encode(userEntity.getPassword()));

        return userMapper.toResponseDto(userEntity);
    }

    @Override
    @Transactional
    public void verify(String email, String code) {
        UserEntity byEmail = userRepository.findByEmail(email);
        if (byEmail == null) {
            throw new ResourceNotFoundException(Message.USER_NOT_FOUND);
        }
        if (!byEmail.getCode().equals(code)) {
            throw new VerificationException(Message.INCORRECT_CODE);
        }
        byEmail.setIsVerified(true);
        userRepository.save(byEmail);
    }

    @Override
    @Transactional
    public List<UserResponseDto> filter(FilterSearchUser.Filter userFilter) {
        Specification<UserEntity> specification = Specification.where((root, criteriaQuery, criteriaBuilder) -> {
            var predicates = new ArrayList<Predicate>();

            if (nonNull(userFilter.getIsVerified())) {
                Predicate isVerified = criteriaBuilder.equal(root.get(Root.IS_VERIFIED), userFilter.getIsVerified());
                predicates.add(isVerified);
            }
            if (nonNull(userFilter.getGender())) {
                Predicate gender = criteriaBuilder.equal(root.get(Root.GENDER), userFilter.getGender());
                predicates.add(gender);
            }
            if (nonNull(userFilter.getStartAge())) {
                Predicate startAge = criteriaBuilder.greaterThan(root.get(Root.AGE), userFilter.getStartAge());
                predicates.add(startAge);
            }
            if (nonNull(userFilter.getEndAge())) {
                Predicate endAge = criteriaBuilder.lessThan(root.get(Root.AGE), userFilter.getEndAge());
                predicates.add(endAge);
            }
            /*if (nonNull(userFilter.getProductName())) {
                Join<UserEntity, ProductEntity> products = root.join("productEntities");
                Predicate productName = criteriaBuilder.equal(products.get("name"), userFilter.getProductName());
                predicates.add(productName);
            }*/


            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        });
        return userMapper.toResponseDtoList(userRepository.findAll(specification));
    }

    @Override
    @Transactional
    public List<UserResponseDto> search(FilterSearchUser.Search userSearch) {
        Specification<UserEntity> specification = Specification.where((root, criteriaQuery, criteriaBuilder) -> {
            var predicates = new ArrayList<Predicate>();

            if (nonNull(userSearch.getText())) {
                Predicate nameLike = criteriaBuilder.like(root.get(Root.NAME), "%" + userSearch.getText() + "%");
                predicates.add(nameLike);

                Predicate lastNameLike = criteriaBuilder.like(root.get(Root.LASTNAME), "%" + userSearch.getText() + "%");
                predicates.add(lastNameLike);

                Predicate emailLike = criteriaBuilder.like(root.get(Root.EMAIL), "%" + userSearch.getText() + "%");
                predicates.add(emailLike);
            }
            return criteriaBuilder.or(predicates.toArray(new Predicate[0]));
        });
        return userMapper.toResponseDtoList(userRepository.findAll(specification));
    }

    private static void setUserAge(UserEntity user) {
        int currentYear = Year.now().getValue();
        int age = currentYear - user.getDate().getYear();
        user.setAge(age);
    }


}
