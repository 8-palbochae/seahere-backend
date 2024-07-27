package com.seahere.backend.inventory.entity;


import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@NoArgsConstructor
public class ProductsEntity {
    @Id
    @GeneratedValue
    @Column(name = "products_id")
    private Long productsId;

    @Column(name = "company_id")
    private Long companyId;

    @OneToOne
    @JoinColumn(name = "item_id")
    private ItemEntity item;

    @OneToMany(mappedBy = "products")
    private List<ProductEntity> productslist = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "inventory_id")
    private InventoryEntity inventory;

    @Column(name = "latest_incoming")
    private Date latestIncoming;

    @Column(name = "total_quantity")
    private int totalQuantity;
}
