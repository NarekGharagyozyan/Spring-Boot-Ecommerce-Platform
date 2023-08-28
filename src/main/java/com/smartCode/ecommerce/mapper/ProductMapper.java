package com.smartCode.ecommerce.mapper;

import com.smartCode.ecommerce.model.dto.product.ProductRequestDto;
import com.smartCode.ecommerce.model.dto.product.ProductResponseDto;
import com.smartCode.ecommerce.model.dto.product.productDetails.ProductDetails;
import com.smartCode.ecommerce.model.dto.user.UserRequestDto;
import com.smartCode.ecommerce.model.dto.user.UserResponseDto;
import com.smartCode.ecommerce.model.entity.product.ProductEntity;
import com.smartCode.ecommerce.model.entity.user.UserEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    ProductEntity toEntity (ProductRequestDto productRequestDto);

    ProductDetails toProductDetails(ProductEntity productEntity);

    ProductResponseDto toResponseDto(ProductEntity productEntity);

    List<ProductResponseDto> toResponseDtoList(List<ProductEntity> productEntities);

}
