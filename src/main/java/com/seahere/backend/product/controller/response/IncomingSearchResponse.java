package com.seahere.backend.product.controller.response;

import com.seahere.backend.product.dto.ProductMockDto;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class IncomingSearchResponse {

    private final Long productId;
    private final String productName;

    public static IncomingSearchResponse from(ProductMockDto dto){
        return IncomingSearchResponse.builder().productId(dto.getProductId()).productName(dto.getProductName()).build();
    }

}
