package com.seahere.backend.product.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name="product")
@NoArgsConstructor
@Getter
public class ProductEntity {
    @Id
    @GeneratedValue
    private Long productId;

    private String productName;
    private String qr;
    private String productImg;

    public ProductEntity(Long productId, String productName, String qr, String productImg) {
        this.productId = productId;
        this.productName = productName;
        this.qr = qr;
        this.productImg = productImg;

    }
}
