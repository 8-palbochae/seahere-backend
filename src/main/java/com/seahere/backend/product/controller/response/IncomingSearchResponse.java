package com.seahere.backend.product.controller.response;

import com.seahere.backend.product.dto.ProductDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@Getter
@NoArgsConstructor
public class IncomingSearchResponse {

    private Long productId;
    private String productName;

    @Builder
    public IncomingSearchResponse(Long productId, String productName) {
        this.productId = productId;
        this.productName = productName;
    }
    public static IncomingSearchResponse from(ProductDto dto){
        return IncomingSearchResponse.builder().productId(dto.getProductId()).productName(dto.getProductName()).build();
    }

}
