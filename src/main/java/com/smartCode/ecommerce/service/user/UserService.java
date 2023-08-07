package com.smartCode.ecommerce.service.user;

import com.smartCode.ecommerce.model.dto.user.UserAuthDto;
import com.smartCode.ecommerce.model.dto.user.UserRequestDto;
import com.smartCode.ecommerce.model.dto.user.UserResponseDto;
import com.smartCode.ecommerce.model.dto.user.UserUpdateDto;
import com.smartCode.ecommerce.model.dto.user.filterAndSearch.FilterSearchUser;

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

    UserAuthDto login(String username, String password);

    void logout();

    void changePassword(String password, String newPassword ,String repeatPassword);
}
