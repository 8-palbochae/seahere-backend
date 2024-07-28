package com.seahere.backend.inventory.entity;


import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Table(name = "product")
@Entity
@Getter
public class ProductEntity {
    @Id
    @GeneratedValue
    @Column(name = "product_id")
    private int productId;

    @Column(name = "qr")
    private String qr;

    @Column(name = "name")
    private String name;

    @Column(name = "image_url")
    private String imageUrl;

}
