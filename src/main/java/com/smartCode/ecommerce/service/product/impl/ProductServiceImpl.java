package com.smartCode.ecommerce.service.product.impl;

import com.smartCode.ecommerce.exceptions.ResourceNotFoundException;
import com.smartCode.ecommerce.mapper.ProductMapper;
import com.smartCode.ecommerce.model.dto.product.ProductRequestDto;
import com.smartCode.ecommerce.model.dto.product.ProductResponseDto;
import com.smartCode.ecommerce.model.dto.product.ProductUpdateDto;
import com.smartCode.ecommerce.model.dto.product.filterAndSearch.FilterSearchProduct;
import com.smartCode.ecommerce.model.dto.user.filterAndSearch.FilterSearchUser;
import com.smartCode.ecommerce.model.entity.product.ProductEntity;
import com.smartCode.ecommerce.repository.product.ProductRepository;
import com.smartCode.ecommerce.service.action.ActionService;
import com.smartCode.ecommerce.service.product.ProductService;
import com.smartCode.ecommerce.util.constants.Action;
import com.smartCode.ecommerce.util.constants.Entity;
import com.smartCode.ecommerce.util.constants.Message;
import com.smartCode.ecommerce.util.constants.Root;
import com.smartCode.ecommerce.util.currentUser.CurrentUser;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.Predicate;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.nonNull;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final ActionService actionService;

    @Override
    @Transactional
    public ProductResponseDto create(ProductRequestDto productRequestDto) {
        ProductEntity productEntity = productMapper.toEntity(productRequestDto);
        setProductDeadline(productEntity);
        ProductEntity save = productRepository.save(productEntity);
        actionService.createAction(Action.CREATE, Entity.PRODUCT, CurrentUser.getId());
        return productMapper.toResponseDto(save);
    }

    @Override
    @Transactional(readOnly = true)
    public ProductResponseDto findProductById(Integer id) {
        return productMapper.toResponseDto(productRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException(Message.PRODUCT_NOT_FOUND)));
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ProductResponseDto> findAllProducts(PageRequest pageRequest) {
        return productRepository.findAll(pageRequest).map(productMapper::toResponseDto);
    }

    @Override
    @Transactional
    public ProductResponseDto updateProduct(Integer id, ProductUpdateDto productUpdateDto) {
        ProductEntity excistingProductEntity = productRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException(Message.PRODUCT_NOT_FOUND));

        excistingProductEntity.setName(productUpdateDto.getName());
        excistingProductEntity.setCount(productUpdateDto.getCount());
        excistingProductEntity.setPrice(productUpdateDto.getPrice());
        excistingProductEntity.setCategory(productUpdateDto.getCategory());

        productRepository.save(excistingProductEntity);
        actionService.createAction(Action.UPDATE, Entity.PRODUCT, CurrentUser.getId());
        return productMapper.toResponseDto(excistingProductEntity);
    }

    @Override
    @Transactional
    public ProductResponseDto updatePartProduct(Integer id, ProductUpdateDto productUpdateDto) {
        ProductEntity existingProd = productRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException(Message.PRODUCT_NOT_FOUND));

        existingProd.setCompany(nonNull(productUpdateDto.getCompany()) ? productUpdateDto.getCompany() : existingProd.getCompany());
        existingProd.setName(nonNull(productUpdateDto.getName()) ? productUpdateDto.getName() : existingProd.getName());
        existingProd.setCategory(nonNull(productUpdateDto.getCategory()) ? productUpdateDto.getCategory() : existingProd.getCategory());
        existingProd.setPrice(nonNull(productUpdateDto.getPrice()) ? productUpdateDto.getPrice() : existingProd.getPrice());
        existingProd.setCount(productUpdateDto.getCount() != 0 ? productUpdateDto.getCount() : existingProd.getCount());

        productRepository.save(existingProd);
        actionService.createAction(Action.UPDATE, Entity.PRODUCT, CurrentUser.getId());
        return productMapper.toResponseDto(existingProd);
    }

    @Override
    @Transactional
    public List<ProductResponseDto> filter(FilterSearchProduct.Filter productFilter) {
        Specification<ProductEntity> specification = Specification.where((root, criteriaQuery, criteriaBuilder) -> {
            var predicates = new ArrayList<Predicate>();

            if (nonNull(productFilter.getStartCount())) {
                Predicate isVerified = criteriaBuilder.greaterThan(root.get(Root.COUNT), productFilter.getStartCount());
                predicates.add(isVerified);
            }
            if (nonNull(productFilter.getEndCount())) {
                Predicate startCount = criteriaBuilder.lessThan(root.get(Root.COUNT), productFilter.getEndCount());
                predicates.add(startCount);
            }
            if (nonNull(productFilter.getStartPrice())) {
                Predicate startPrice = criteriaBuilder.greaterThan(root.get(Root.PRICE), productFilter.getStartPrice());
                predicates.add(startPrice);
            }
            if (nonNull(productFilter.getEndPrice())) {
                Predicate endPrice = criteriaBuilder.lessThan(root.get(Root.PRICE), productFilter.getEndPrice());
                predicates.add(endPrice);
            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        });
        return productMapper.toResponseDtoList(productRepository.findAll(specification));
    }

    @Override
    @Transactional
    public List<ProductResponseDto> search(FilterSearchUser.Search productSearch) {
        Specification<ProductEntity> specification = Specification.where((root, criteriaQuery, criteriaBuilder) -> {
            var predicates = new ArrayList<Predicate>();

            if (nonNull(productSearch.getText())) {
                Predicate nameLike = criteriaBuilder.like(criteriaBuilder.lower(root.get(Root.NAME)), "%" + productSearch.getText().toLowerCase() + "%");
                predicates.add(nameLike);

                Predicate lastNameLike = criteriaBuilder.like(criteriaBuilder.lower(root.get(Root.COMPANY)), "%" + productSearch.getText().toLowerCase() + "%");
                predicates.add(lastNameLike);

                Predicate emailLike = criteriaBuilder.like(criteriaBuilder.lower(root.get(Root.CATEGORY)), "%" + productSearch.getText().toLowerCase() + "%");
                predicates.add(emailLike);
            }
            return criteriaBuilder.or(predicates.toArray(new Predicate[0]));
        });
        return productMapper.toResponseDtoList(productRepository.findAll(specification));
    }

    @Override
    @Transactional
    public void deleteProduct(Integer id) {
        productRepository.deleteById(id);
        actionService.createAction(Action.DELETE, Entity.PRODUCT, CurrentUser.getId());
    }

    private static void setProductDeadline(ProductEntity productEntity) {
        if (productEntity.getCategory().equalsIgnoreCase(Root.GROCERY)) {
            if (LocalDate.now().getDayOfMonth() - productEntity.getProductionDate().getDayOfMonth() < 4)
                productEntity.setIsInDeadline(true);
            else
                productEntity.setIsInDeadline(false);
        }
    }
}
