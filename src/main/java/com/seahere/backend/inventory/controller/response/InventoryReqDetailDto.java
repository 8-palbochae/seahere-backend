package com.seahere.backend.inventory.controller.response;

import lombok.Getter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@ToString
public class InventoryReqDetailDto {
    private Long inventoryId;
    private Long companyId;
    private String name;
    private String category;
    private int quantity;
    private LocalDate incomingDate;
    private String country;
    private String naturalStatus;

    public InventoryReqDetailDto(Long inventoryId, Long companyId, String name, String category, int quantity, LocalDate incomingDate, String country, String naturalStatus) {
        this.inventoryId = inventoryId;
        this.companyId = companyId;
        this.name = name;
        this.category = category;
        this.quantity = quantity;
        this.incomingDate = incomingDate;
        this.country = country;
        this.naturalStatus = naturalStatus;
    }
}
