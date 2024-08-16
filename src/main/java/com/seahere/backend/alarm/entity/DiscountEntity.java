package com.seahere.backend.alarm.entity;

import com.seahere.backend.inventory.entity.InventoryEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "discount")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DiscountEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long discountId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "inventory_id")
    private InventoryEntity inventory;

    private LocalDate startDate;
    private LocalDate endDate;

    private BigDecimal discountPrice;
    private BigDecimal originalPrice;
}
