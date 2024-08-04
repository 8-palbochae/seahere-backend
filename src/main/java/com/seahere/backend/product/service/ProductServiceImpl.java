package com.seahere.backend.product.service;

import com.seahere.backend.product.entity.ProductEntity;
import com.seahere.backend.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class ProductServiceImpl implements ProductService{

    private final ProductRepository productRepository;

    @Override
    public List<ProductEntity> getAllProducts() {
        return productRepository.findAll().stream().collect(Collectors.toList());
    }
}
