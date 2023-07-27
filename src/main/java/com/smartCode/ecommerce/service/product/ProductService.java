package com.smartCode.ecommerce.service.product;


import com.smartCode.ecommerce.model.dto.product.ProductUpdateDto;
import com.smartCode.ecommerce.model.dto.product.filterAndSearch.FilterSearchProduct;
import com.smartCode.ecommerce.model.dto.user.filterAndSearch.FilterSearchUser;
import com.smartCode.ecommerce.model.entity.product.ProductEntity;

import java.util.List;

public interface ProductService {

    ProductEntity create(ProductEntity productEntity);

    ProductEntity findProductById(Integer id);

    List<ProductEntity> findAllProducts();

    ProductEntity updateProduct(Integer id, ProductUpdateDto productUpdateDto);

    ProductEntity updatePartProduct(Integer id, ProductUpdateDto productUpdateDto);
    Boolean deleteProduct(Integer id);

    List<ProductEntity> filter(FilterSearchProduct.Filter productFilter);

    List<ProductEntity> search(FilterSearchUser.Search text);

}
