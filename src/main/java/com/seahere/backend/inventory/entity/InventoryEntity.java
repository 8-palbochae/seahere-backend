package com.seahere.backend.inventory.entity;

import lombok.NoArgsConstructor;
import javax.persistence.*;

@Entity
@NoArgsConstructor
public class InventoryEntity {
    @Id
    @GeneratedValue
    @Column(name = "inventory_id")
    private Long inventoryId;

    @Column(name = "incoming_id")
    private Long companyId;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "incoming_price")
    private int incomingPrice;
}
