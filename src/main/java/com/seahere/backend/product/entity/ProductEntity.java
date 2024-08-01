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
    private Long productId;

    private String productName;
    private String qr;
    private String productImg;

}
