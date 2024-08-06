package com.seahere.backend.inventory.controller.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GeneratorType;

import java.math.BigDecimal;

@Getter
@NoArgsConstructor
public class InventorySettingRes {
    private Long id;
    private int warningQuantity;
    private BigDecimal outgoingPrice;

    @Builder
    public InventorySettingRes(Long id, int warningQuantity, BigDecimal outgoingPrice) {
        this.id = id;
        this.warningQuantity = warningQuantity;
        this.outgoingPrice = outgoingPrice;
    }
}
