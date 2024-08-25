package com.seahere.backend.product.service;

import com.seahere.backend.product.dto.ProductDto;
import com.seahere.backend.product.entity.ProductDocument;
import com.seahere.backend.product.entity.ProductEntity;
import com.seahere.backend.product.repository.ProductElasticsearchRepository;
import com.seahere.backend.product.repository.ProductJpaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class ProductServiceImpl implements ProductService{

    private final ProductJpaRepository productJpaRepository;
    private final ProductElasticsearchRepository productElasticsearchRepository;

    @Override
    public List<ProductEntity> getAllProducts() {
        return new ArrayList<>(productJpaRepository.findAll());
    }

    @Override
    public Optional<ProductEntity> getProduct(Long productId) {
        return productJpaRepository.findById(productId);
    }

    @Override
    public List<ProductDto> searchProductsWithFuzzy(String query) {
        try {
            // ProductElsRepository를 사용하여 엘라스틱서치에서 검색 수행
            log.info("query = {}",query);
            List<ProductDocument> productEntities = productElasticsearchRepository.findByProductName(query);
            return productEntities.stream()
                    .map(entity -> ProductDto.builder()
                            .productId(entity.getProductId())
                            .productName(entity.getProductName())
                            .build())
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new RuntimeException("Failed to search products by name using repository", e);
        }
    }
}
