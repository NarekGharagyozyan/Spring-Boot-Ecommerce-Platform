package com.smartCode.ecommerce.mapper;

import com.smartCode.ecommerce.model.dto.user.UserRequestDto;
import com.smartCode.ecommerce.model.dto.user.UserResponseDto;
import com.smartCode.ecommerce.model.entity.user.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "midName", source = "middleName")
    UserEntity toEntity (UserRequestDto userRequestDto);

    @Mapping(target = "middleName", source = "midName")
    UserResponseDto toResponseDto(UserEntity userEntity);

    List<UserResponseDto>  toResponseDtoList(List<UserEntity> userEntities);
}
