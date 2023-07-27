package com.smartCode.ecommerce.service.user;

import com.smartCode.ecommerce.model.dto.user.UserUpdateDto;
import com.smartCode.ecommerce.model.dto.user.filterAndSearch.FilterSearchUser;
import com.smartCode.ecommerce.model.entity.user.UserEntity;

import java.util.List;

public interface UserService {
    UserEntity register(UserEntity user);
    UserEntity getById(Integer id);

    List<UserEntity> getAllUsers();

    UserEntity update(Integer id, UserUpdateDto userUpdateDto);

    Boolean delete(Integer id);

    Boolean verify(String email, String code);

    List<UserEntity> filter(FilterSearchUser.Filter userFilter);

    List<UserEntity> search(FilterSearchUser.Search text);

    UserEntity login(String username, String password);

    Boolean changePassword(String email, String password, String newPassword ,String repeatPassword);
}
