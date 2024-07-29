package com.seahere.backend.inventory.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@NoArgsConstructor
@Table(name = "inventories")
@Getter
@AllArgsConstructor
@Builder
public class InventoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "inventory_id")
    private Long inventoryId;

    @Column(name = "company_id")
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
    private LocalDate incomingDate;

    @Column(name = "natural_status")
    private String naturalStatus;
}
