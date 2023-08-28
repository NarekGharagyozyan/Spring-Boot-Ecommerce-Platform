package com.smartCode.ecommerce.converter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.smartCode.ecommerce.model.dto.product.productDetails.ProductDetails;
import lombok.RequiredArgsConstructor;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
@RequiredArgsConstructor
public class ProductDetailsConverter implements AttributeConverter<ProductDetails, String> {

    private final ObjectMapper objectMapper;

    @Override
    public String convertToDatabaseColumn(ProductDetails productDetails) {
        try {
            return objectMapper.writeValueAsString(productDetails);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ProductDetails convertToEntityAttribute(String json) {
        try {
            return objectMapper.readValue(json, ProductDetails.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}