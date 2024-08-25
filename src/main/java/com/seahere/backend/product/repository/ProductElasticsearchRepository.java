package com.seahere.backend.product.repository;

import com.seahere.backend.product.entity.ProductDocument;
import com.seahere.backend.product.entity.ProductEntity;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface ProductElasticsearchRepository extends ElasticsearchRepository<ProductDocument, Long> {
    List<ProductDocument> findByProductName(String productName);

}
