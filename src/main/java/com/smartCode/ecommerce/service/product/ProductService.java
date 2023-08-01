package com.smartCode.ecommerce.service.product;


import com.smartCode.ecommerce.model.dto.product.ProductResponseDto;
import com.smartCode.ecommerce.model.dto.product.ProductUpdateDto;
import com.smartCode.ecommerce.model.dto.product.filterAndSearch.FilterSearchProduct;
import com.smartCode.ecommerce.model.dto.user.filterAndSearch.FilterSearchUser;
import com.smartCode.ecommerce.model.entity.product.ProductEntity;

import java.util.List;

public interface ProductService {

    ProductResponseDto create(ProductEntity productEntity);

    ProductResponseDto findProductById(Integer id);

    List<ProductResponseDto> findAllProducts();

    ProductResponseDto updateProduct(Integer id, ProductUpdateDto productUpdateDto);

    ProductResponseDto updatePartProduct(Integer id, ProductUpdateDto productUpdateDto);
    void deleteProduct(Integer id);

    List<ProductResponseDto> filter(FilterSearchProduct.Filter productFilter);

    List<ProductResponseDto> search(FilterSearchUser.Search text);

}
