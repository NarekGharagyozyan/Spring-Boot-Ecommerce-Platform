package com.smartCode.ecommerce.controller;

import com.smartCode.ecommerce.mapper.UserMapper;
import com.smartCode.ecommerce.model.dto.user.UserRequestDto;
import com.smartCode.ecommerce.model.dto.user.UserResponseDto;
import com.smartCode.ecommerce.model.dto.user.UserUpdateDto;
import com.smartCode.ecommerce.model.dto.user.filterAndSearch.FilterSearchUser;
import com.smartCode.ecommerce.model.entity.user.UserEntity;
import com.smartCode.ecommerce.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Positive;
import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@Validated
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;

    @PostMapping
    public ResponseEntity<UserResponseDto> create(@RequestBody UserRequestDto user) {
        UserEntity entity = userMapper.toEntity(user);
        UserEntity register = userService.register(entity);
        return ResponseEntity.ok(userMapper.toResponseDto(register));
    }

    @PostMapping("/login")
    public ResponseEntity<UserResponseDto> login(@RequestParam String username,
                                                 @RequestParam String password) {

        UserEntity loginUser = userService.login(username, password);
        UserResponseDto responseDto = userMapper.toResponseDto(loginUser);
        return ResponseEntity.ok(responseDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDto> getById(@PathVariable @Positive Integer id) {
        UserEntity userEntity = userService.getById(id);
        UserResponseDto responseDto = userMapper.toResponseDto(userEntity);
        return ResponseEntity.ok(responseDto);
    }

    @GetMapping
    public ResponseEntity<List<UserResponseDto>> getAllUsers() {
        List<UserEntity> allUsers = userService.getAllUsers();
        List<UserResponseDto> responseDtoList = userMapper.toResponseDtoList(allUsers);
        return ResponseEntity.ok(responseDtoList);
    }


    @PatchMapping("/{id}")
    public ResponseEntity<UserResponseDto> updateUserPartial(@PathVariable Integer id,
                                                      @RequestBody UserUpdateDto userUpdateDto) {
        UserEntity updatedUser = userService.update(id, userUpdateDto);
        return ResponseEntity.ok(userMapper.toResponseDto(updatedUser));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteUser(@PathVariable Integer id) {
        Boolean isDeleted = userService.delete(id);
        return ResponseEntity.ok(isDeleted);
    }

    @PostMapping("/verify")
    public ResponseEntity<Boolean> verifyUser(@RequestParam String email,
                                              @RequestParam String code) {
        Boolean verify = userService.verify(email, code);
        return ResponseEntity.ok(verify);
    }

    @PostMapping("/changePassword")
    public ResponseEntity<Boolean> verifyUser(@RequestParam String email,
                                              @RequestParam String password,
                                              @RequestParam String newPassword,
                                              @RequestParam String newRepeatPassword) {

        Boolean isChanged = userService.changePassword(email, password, newPassword, newRepeatPassword);
        return ResponseEntity.ok(isChanged);
    }

    @PostMapping("/filter")
    public ResponseEntity<List<UserResponseDto>> filter(@RequestBody FilterSearchUser.Filter userFilter) {
        List<UserEntity> userEntities = userService.filter(userFilter);
        List<UserResponseDto> responseDtoList = userMapper.toResponseDtoList(userEntities);
        return ResponseEntity.ok(responseDtoList);
    }

    @PostMapping("/search")
    public ResponseEntity<List<UserResponseDto>> search(@RequestBody FilterSearchUser.Search text) {
        List<UserEntity> userEntities = userService.search(text);
        List<UserResponseDto> responseDtoList = userMapper.toResponseDtoList(userEntities);
        return ResponseEntity.ok(responseDtoList);
    }
}
