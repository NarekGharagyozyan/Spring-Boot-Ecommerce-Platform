package com.smartCode.ecommerce.mapper;

import com.smartCode.ecommerce.model.dto.user.UserRequestDto;
import com.smartCode.ecommerce.model.dto.user.UserResponseDto;
import com.smartCode.ecommerce.model.entity.user.UserEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserEntity toEntity (UserRequestDto userRequestDto);

    UserResponseDto toResponseDto(UserEntity userEntity);

    List<UserResponseDto>  toResponseDtoList(List<UserEntity> userEntities);
}
