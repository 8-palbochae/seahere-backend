package com.seahere.backend.inventory.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@NoArgsConstructor
@Table(name = "inventory")
@Getter
@Setter
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

    @Column(name = "category")
    private String category;

    @Column(name = "name")
    private String name;

    @Column(name = "country")
    private String country;

    @Column(name = "incoming_date")
    private Date incomingDate;

    @Column(name = "natural")
    private String natural;
}
