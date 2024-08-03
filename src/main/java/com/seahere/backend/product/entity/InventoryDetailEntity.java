package com.seahere.backend.product.entity;

import com.seahere.backend.common.entity.ProductCountry;
import com.seahere.backend.common.entity.ProductStatus;
import com.seahere.backend.company.entity.CompanyEntity;
import com.seahere.backend.inventory.entity.InventoryEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name="inventory_detail")
@NoArgsConstructor
@Getter
public class InventoryDetailEntity {
    @Id
    @GeneratedValue
    @Column(name = "inventory_detail_id")
    private Long id;

    @OneToOne
    @JoinColumn(name = "inventory_id")
    private InventoryEntity inventory;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id")
    private CompanyEntity company;

    private int warningQuantity;

    private BigDecimal price;

    @Builder
    public InventoryDetailEntity(Long id, InventoryEntity inventory, CompanyEntity company, int warningQuantity, BigDecimal price) {
        this.id = id;
        this.inventory = inventory;
        this.company = company;
        this.warningQuantity = warningQuantity;
        this.price = price;
    }

    public void editQuantity(int warningQuantity){
        this.warningQuantity = warningQuantity;
    }

    public void editPrice(BigDecimal price){
        this.price =price;
    }
}
