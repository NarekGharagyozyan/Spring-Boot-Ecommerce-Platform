package com.smartCode.ecommerce.service.user;

import com.smartCode.ecommerce.model.dto.user.UserAuthDto;
import com.smartCode.ecommerce.model.dto.user.UserRequestDto;
import com.smartCode.ecommerce.model.dto.user.UserResponseDto;
import com.smartCode.ecommerce.model.dto.user.UserUpdateDto;
import com.smartCode.ecommerce.model.dto.user.filterAndSearch.FilterSearchUser;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface UserService {
    UserResponseDto register(UserRequestDto user);
    UserResponseDto getById(Integer id);

    List<UserResponseDto> getAllUsers();

    UserResponseDto update(Integer id, UserUpdateDto userUpdateDto);

    void delete(Integer id);

    void verify(String email, String code);

    List<UserResponseDto> filter(FilterSearchUser.Filter userFilter);

    List<UserResponseDto> search(FilterSearchUser.Search text);

    void logout(String token);

    UserAuthDto login(String username, String password);

    void changePassword(String password, String newPassword ,String repeatPassword);
}
