package com.smartCode.ecommerce.controller;

import com.smartCode.ecommerce.mapper.ProductMapper;
import com.smartCode.ecommerce.model.dto.product.ProductResponseDto;
import com.smartCode.ecommerce.model.dto.product.ProductUpdateDto;
import com.smartCode.ecommerce.model.dto.product.filterAndSearch.FilterSearchProduct;
import com.smartCode.ecommerce.model.dto.user.filterAndSearch.FilterSearchUser;
import com.smartCode.ecommerce.model.entity.product.ProductEntity;
import com.smartCode.ecommerce.service.product.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;
    private final ProductMapper productMapper;


    @GetMapping("/{id}")
    public ResponseEntity<ProductResponseDto> findProduct(@PathVariable Integer id) {
        ProductEntity productEntityById = productService.findProductById(id);
        ProductResponseDto responseDto = productMapper.toResponseDto(productEntityById);
        return ResponseEntity.ok(responseDto);
    }

    @GetMapping
    public ResponseEntity<List<ProductResponseDto>> findAllProducts() {
        List<ProductEntity> allProductEntities = productService.findAllProducts();
        List<ProductResponseDto> responseDtoList = productMapper.toResponseDtoList(allProductEntities);
        return ResponseEntity.ok(responseDtoList);
    }

    @PostMapping
    public ResponseEntity<ProductResponseDto> createProduct(@RequestBody ProductEntity productEntity) {
        ProductEntity responseEntity = productService.create(productEntity);
        ProductResponseDto responseDto = productMapper.toResponseDto(responseEntity);
        return ResponseEntity.ok(responseDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductResponseDto> updateProduct(@PathVariable Integer id,
                                                            @RequestBody ProductUpdateDto productUpdate) {

        ProductEntity responseEntity = productService.updateProduct(id, productUpdate);
        ProductResponseDto responseDto = productMapper.toResponseDto(responseEntity);
        return ResponseEntity.ok(responseDto);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ProductResponseDto> updatePartProduct(@PathVariable Integer id,
                                                                @RequestBody ProductUpdateDto productUpdate) {
        ProductEntity responseEntity = productService.updatePartProduct(id, productUpdate);
        ProductResponseDto responseDto = productMapper.toResponseDto(responseEntity);
        return ResponseEntity.ok(responseDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteProduct(@PathVariable Integer id) {
        Boolean isDeleted = productService.deleteProduct(id);
        return ResponseEntity.ok(isDeleted);
    }

    @PostMapping("/filter")
    public ResponseEntity<List<ProductResponseDto>> filterProduct(@RequestBody FilterSearchProduct.Filter productFilter) {
        List<ProductEntity> productEntities = productService.filter(productFilter);
        List<ProductResponseDto> responseDtoList = productMapper.toResponseDtoList(productEntities);
        return ResponseEntity.ok(responseDtoList);
    }

    @PostMapping("/search")
    public ResponseEntity<List<ProductResponseDto>> search(@RequestBody FilterSearchUser.Search text) {
        List<ProductEntity> productEntities = productService.search(text);
        List<ProductResponseDto> responseDtoList = productMapper.toResponseDtoList(productEntities);
        return ResponseEntity.ok(responseDtoList);
    }
}
