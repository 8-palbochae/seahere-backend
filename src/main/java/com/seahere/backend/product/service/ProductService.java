package com.seahere.backend.product.service;

import com.seahere.backend.product.entity.ProductEntity;

import java.util.List;

public interface ProductService {
    List<ProductEntity> getAllProducts();
}
