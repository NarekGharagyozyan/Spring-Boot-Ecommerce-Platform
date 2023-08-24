package com.smartCode.ecommerce.service.product;


import com.smartCode.ecommerce.model.dto.product.ProductResponseDto;
import com.smartCode.ecommerce.model.dto.product.ProductUpdateDto;
import com.smartCode.ecommerce.model.dto.product.filterAndSearch.FilterSearchProduct;
import com.smartCode.ecommerce.model.dto.user.filterAndSearch.FilterSearchUser;
import com.smartCode.ecommerce.model.entity.product.ProductEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface ProductService {

    ProductResponseDto create(ProductEntity productEntity);

    ProductResponseDto findProductById(Integer id);

    Page<ProductResponseDto> findAllProducts(PageRequest pageRequest);

    ProductResponseDto updateProduct(Integer id, ProductUpdateDto productUpdateDto);

    ProductResponseDto updatePartProduct(Integer id, ProductUpdateDto productUpdateDto);
    void deleteProduct(Integer id);

    List<ProductResponseDto> filter(FilterSearchProduct.Filter productFilter);

    List<ProductResponseDto> search(FilterSearchUser.Search text);

}
