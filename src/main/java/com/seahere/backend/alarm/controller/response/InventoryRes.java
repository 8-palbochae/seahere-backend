package com.seahere.backend.alarm.controller.response;

import com.seahere.backend.inventory.entity.InventoryEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Getter
@NoArgsConstructor
public class InventoryRes {
    private Long inventoryId;
    private String name;
    private String imgUrl;
    private String category;
    private String country;
    private String naturalStatus;
    private BigDecimal price;
    private float quantity;
    private BigDecimal discountPrice;
    @Builder
    public InventoryRes(Long inventoryId, String name, String imgUrl, String category, String country, String naturalStatus, BigDecimal price, float quantity) {
        this.inventoryId = inventoryId;
        this.name = name;
        this.imgUrl = imgUrl;
        this.category = category;
        this.country = country;
        this.naturalStatus = naturalStatus;
        this.price = price;
        this.quantity = quantity;
    }

    public static InventoryRes from(InventoryEntity inventory){
        return InventoryRes.builder()
                .inventoryId(inventory.getInventoryId())
                .name(inventory.getProduct().getProductName())
                .imgUrl(inventory.getProduct().getProductImg())
                .price(inventory.getInventoryDetail().getOutgoingPrice())
                .country(inventory.getCountry())
                .category(inventory.getCategory())
                .naturalStatus(inventory.getNaturalStatus())
                .quantity(inventory.getQuantity())
                .build();
    }
}
