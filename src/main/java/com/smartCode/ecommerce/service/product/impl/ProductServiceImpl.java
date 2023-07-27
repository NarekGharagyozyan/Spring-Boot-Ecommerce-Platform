package com.smartCode.ecommerce.service.product.impl;

import com.smartCode.ecommerce.model.dto.product.ProductUpdateDto;
import com.smartCode.ecommerce.model.dto.product.filterAndSearch.FilterSearchProduct;
import com.smartCode.ecommerce.model.dto.user.filterAndSearch.FilterSearchUser;
import com.smartCode.ecommerce.model.entity.product.ProductEntity;
import com.smartCode.ecommerce.repository.ProductRepository;
import com.smartCode.ecommerce.service.product.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.nonNull;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;


    @Override
    @Transactional
    public ProductEntity create(ProductEntity productEntity) {
        return productRepository.save(productEntity);
    }

    @Override
    @Transactional
    public ProductEntity findProductById(Integer id) {
        return productRepository.findById(id).get();
    }

    @Override
    @Transactional
    public List<ProductEntity> findAllProducts() {
        return productRepository.findAll();
    }

    @Override
    @Transactional
    public ProductEntity updateProduct(Integer id, ProductUpdateDto productUpdateDto) {
        ProductEntity excistingProductEntity = productRepository.findById(id).get();

        excistingProductEntity.setName(productUpdateDto.getName());
        excistingProductEntity.setCount(productUpdateDto.getCount());
        excistingProductEntity.setPrice(productUpdateDto.getPrice());
        excistingProductEntity.setCategory(productUpdateDto.getCategory());

        productRepository.save(excistingProductEntity);
        return excistingProductEntity;
    }

    @Override
    public ProductEntity updatePartProduct(Integer id, ProductUpdateDto productUpdateDto) {
        ProductEntity existingProd = productRepository.findById(id).orElse(null);

        existingProd.setCompany(nonNull(productUpdateDto.getCompany()) ? productUpdateDto.getCompany() : existingProd.getCompany());
        existingProd.setName(nonNull(productUpdateDto.getName()) ? productUpdateDto.getName() : existingProd.getName());
        existingProd.setCategory(nonNull(productUpdateDto.getCategory()) ? productUpdateDto.getCategory() : existingProd.getCategory());
        existingProd.setPrice(nonNull(productUpdateDto.getPrice()) ? productUpdateDto.getPrice() : existingProd.getPrice());
        existingProd.setCount(productUpdateDto.getCount() != 0 ? productUpdateDto.getCount() : existingProd.getCount());

        productRepository.save(existingProd);
        return existingProd;
    }

    @Override
    public List<ProductEntity> filter(FilterSearchProduct.Filter productFilter) {
        Specification<ProductEntity> specification = Specification.where((root, criteriaQuery, criteriaBuilder) -> {
            var predicates = new ArrayList<Predicate>();

            if (nonNull(productFilter.getStartCount())) {
                Predicate isVerified = criteriaBuilder.greaterThan(root.get("count"), productFilter.getStartCount());
                predicates.add(isVerified);
            }
            if (nonNull(productFilter.getEndCount())) {
                Predicate startCount = criteriaBuilder.lessThan(root.get("count"), productFilter.getEndCount());
                predicates.add(startCount);
            }
            if (nonNull(productFilter.getStartPrice())) {
                Predicate startPrice = criteriaBuilder.greaterThan(root.get("price"), productFilter.getStartPrice());
                predicates.add(startPrice);
            }
            if (nonNull(productFilter.getEndPrice())) {
                Predicate endPrice = criteriaBuilder.lessThan(root.get("price"), productFilter.getEndPrice());
                predicates.add(endPrice);
            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        });
        return productRepository.findAll(specification);
    }

    @Override
    public List<ProductEntity> search(FilterSearchUser.Search productSearch) {
        Specification<ProductEntity> specification = Specification.where((root, criteriaQuery, criteriaBuilder) -> {
            var predicates = new ArrayList<Predicate>();

            if (nonNull(productSearch.getText())) {
                Predicate nameLike = criteriaBuilder.like(root.get("name"), "%" + productSearch.getText() + "%");
                predicates.add(nameLike);

                Predicate lastNameLike = criteriaBuilder.like(root.get("company"), "%" + productSearch.getText() + "%");
                predicates.add(lastNameLike);

                Predicate emailLike = criteriaBuilder.like(root.get("category"), "%" + productSearch.getText() + "%");
                predicates.add(emailLike);
            }
            return criteriaBuilder.or(predicates.toArray(new Predicate[0]));
        });
        return productRepository.findAll(specification);
    }


    @Override
    public Boolean deleteProduct(Integer id) {
        productRepository.deleteById(id);
        return true;
    }
}
