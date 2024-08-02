package com.seahere.backend.inventory.entity;

import com.seahere.backend.company.entity.CompanyEntity;
import com.seahere.backend.product.entity.ProductEntity;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@NoArgsConstructor
@Table(name = "inventories")
@Getter
@AllArgsConstructor
@Builder
@DynamicUpdate
@Setter
public class InventoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "inventory_id")
    private Long inventoryId;

    @ManyToOne
    @JoinColumn(name = "company_id")
    private CompanyEntity company;

    @Column(name = "quantity")
    private float quantity;

    @Column(name = "category")
    private String category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private ProductEntity product;

    @Column(name = "country")
    private String country;

    @Column(name = "incoming_date")
    private LocalDate incomingDate;

    @Column(name = "natural_status")
    private String naturalStatus;

    public void addQuantity(float quantity){
        this.quantity += quantity;
    }

    public void minusQuantity(float quantity){ this.quantity -= quantity;}

    public void assignCompany(CompanyEntity company){
        this.company = company;
    }
}


