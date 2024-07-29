package com.seahere.backend.product.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@ToString
public class ProductMockDto {

    private Long productId;
    private String productName;
    private String qr;
    private String productImg;

}
