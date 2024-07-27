package com.seahere.backend.inventory.entity;

import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "inventory")
@NoArgsConstructor
public class InventoryEntity {
    @Id
    @GeneratedValue
    @Column(name = "inventory_id")
    private int inventoryId;

    @OneToMany(mappedBy = "inventory")
    private List<ProductsEntity> products = new ArrayList<>();
}