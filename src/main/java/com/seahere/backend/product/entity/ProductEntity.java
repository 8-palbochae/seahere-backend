package com.seahere.backend.product.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name="product")
@NoArgsConstructor
@Getter
@AllArgsConstructor
public class ProductEntity {
    @Id
    @GeneratedValue
    @Column(name = "product_id")
    private Long productId;

    @Column(name = "product_name")
    private String productName;

    @Column(name = "qr")
    private String qr;

    @Column(name = "product_img")
    private String productImg;

}
