package com.smartCode.ecommerce.controller;

import com.smartCode.ecommerce.model.dto.product.ProductResponseDto;
import com.smartCode.ecommerce.model.dto.product.ProductUpdateDto;
import com.smartCode.ecommerce.model.dto.product.filterAndSearch.FilterSearchProduct;
import com.smartCode.ecommerce.model.dto.user.filterAndSearch.FilterSearchUser;
import com.smartCode.ecommerce.model.entity.product.ProductEntity;
import com.smartCode.ecommerce.service.product.ProductService;
import com.smartCode.ecommerce.util.constants.Path;
import com.smartCode.ecommerce.util.constants.RoleConstants;
import com.smartCode.ecommerce.util.page.CustomPageRequest;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.util.List;

@RestController
@Validated
@RequiredArgsConstructor
@RequestMapping(Path.PRODUCTS)
public class ProductController {

    private final ProductService productService;

    @GetMapping(Path.FIND)
    public ResponseEntity<ProductResponseDto> findProduct(@PathVariable @Positive Integer id) {
        ProductResponseDto productById = productService.findProductById(id);
        return ResponseEntity.ok(productById);
    }

    @GetMapping(Path.FIND_ALL)
    public ResponseEntity<Page<ProductResponseDto>> findAllProducts(@RequestParam(required = false) Integer page,
                                                                    @RequestParam(required = false) Integer size,
                                                                    @RequestParam(name = "sort", defaultValue = "id") String sort,
                                                                    @RequestParam(required = false, defaultValue = "ASC") Sort.Direction direction) {
        var pageRequest = CustomPageRequest.from(page, size, Sort.by(direction, sort, "id"));
        return ResponseEntity.ok(productService.findAllProducts(pageRequest));
    }

    @PostMapping(Path.CREATE)
    @PreAuthorize("hasRole('" + RoleConstants.ADMIN_ROLE + "')")
    public ResponseEntity<ProductResponseDto> createProduct(@RequestBody @Valid ProductEntity productEntity) {
        ProductResponseDto productResponseDto = productService.create(productEntity);
        return ResponseEntity.ok(productResponseDto);
    }

    @PutMapping(Path.UPDATE_ALL)
    @PreAuthorize("hasRole('" + RoleConstants.ADMIN_ROLE + "')")
    public ResponseEntity<ProductResponseDto> updateProduct(@PathVariable @Positive Integer id,
                                                            @RequestBody @Valid ProductUpdateDto productUpdate) {

        ProductResponseDto productResponseDto = productService.updateProduct(id, productUpdate);
        return ResponseEntity.ok(productResponseDto);
    }

    @PatchMapping(Path.UPDATE)
    @PreAuthorize("hasRole('" + RoleConstants.ADMIN_ROLE + "')")
    public ResponseEntity<ProductResponseDto> updatePartProduct(@PathVariable @Positive Integer id,
                                                                @RequestBody @Valid ProductUpdateDto productUpdate) {
        ProductResponseDto productResponseDto = productService.updatePartProduct(id, productUpdate);
        return ResponseEntity.ok(productResponseDto);
    }

    @DeleteMapping(Path.DELETE)
    @PreAuthorize("hasRole('" + RoleConstants.ADMIN_ROLE + "')")
    public ResponseEntity<Void> deleteProduct(@PathVariable @Positive Integer id) {
        productService.deleteProduct(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping(Path.FILTER)
    public ResponseEntity<List<ProductResponseDto>> filterProduct(@RequestBody @Valid FilterSearchProduct.Filter productFilter) {
        List<ProductResponseDto> responseDtoList = productService.filter(productFilter);
        return ResponseEntity.ok(responseDtoList);
    }

    @PostMapping(Path.SEARCH)
    public ResponseEntity<List<ProductResponseDto>> search(@RequestBody @Valid FilterSearchUser.Search text) {
        List<ProductResponseDto> responseDtoList = productService.search(text);
        return ResponseEntity.ok(responseDtoList);
    }
}
