package com.seahere.backend.product.dto;

import com.seahere.backend.product.entity.ProductEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.Map;

@AllArgsConstructor
@Getter
@ToString
@Builder
public class ProductDto {

    private Long productId;
    private String productName;
    private String qr;
    private String productImg;

    public static ProductDto from(ProductEntity productEntity){
        return ProductDto.builder()
                .productId(productEntity.getProductId())
                .productName(productEntity.getProductName())
                .qr(productEntity.getQr())
                .productImg(productEntity.getProductImg())
                .build();
    }
    // 새로운 Map<String, Object>에서 변환하는 메서드
    public static ProductDto from(Map<String, Object> map) {
        return ProductDto.builder()
                .productId(((Number) map.get("productId")).longValue()) // Long으로 캐스팅
                .productName((String) map.get("productName"))
                .qr((String) map.get("qr"))
                .productImg((String) map.get("productImg"))
                .build();
    }
}