package com.seahere.backend.inventory.entity;


import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@NoArgsConstructor
@Table(name = "product")
@Entity
public class ProductEntity {
    @Id
    @GeneratedValue
    @Column(name = "product_id")
    private int productId;

    @OneToOne
    @JoinColumn(name = "item_id")
    private ItemEntity item;

    @ManyToOne
    @Column(name = "products_id")
    private ProductsEntity products;

    private String category;
    private String country;
    private String qr;
    private String natural;
    private int quantity;
    private int incomingPrice;
    private int outgoingPrice;

    @Column(name = "incoming_date")
    private Date incomingDate;

    public void updateQuantity(int quantity){
        this.quantity = quantity;
    }

    public void updateOutgoingPrice(int outgoingPrice){
        this.outgoingPrice = outgoingPrice;
    }

}
