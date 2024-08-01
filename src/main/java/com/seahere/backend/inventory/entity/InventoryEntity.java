package com.seahere.backend.inventory.entity;

import com.seahere.backend.company.entity.CompanyEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
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

    @Column(name = "name")
    private String name;

    @Column(name = "country")
    private String country;

    @Column(name = "incoming_date")
    private LocalDate incomingDate;

    @Column(name = "natural_status")
    private String naturalStatus;

    public void addQuantity(int quantity){
        this.quantity += quantity;
    }

    public void assignCompany(CompanyEntity company){
        this.company = company;
    }
}


