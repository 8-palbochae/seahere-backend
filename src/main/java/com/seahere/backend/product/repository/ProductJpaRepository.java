package com.seahere.backend.product.repository;

import com.seahere.backend.product.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductJpaRepository extends JpaRepository<ProductEntity, Long>{
}
