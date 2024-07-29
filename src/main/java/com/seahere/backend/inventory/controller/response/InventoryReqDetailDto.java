package com.seahere.backend.inventory.controller.response;

import lombok.Getter;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

@Getter
public class InventoryReqDetailDto {
    private Long companyId;
    private String name;
    private String category;
    private int quantity;
    private int incomingPrice;
    private LocalDate incomingDate;
    private String country;
    private String naturalStatus;

    public InventoryReqDetailDto(Long companyId, String name, String category, long quantity, int incomingPrice, LocalDate incomingDate, String country, String naturalStatus) {
        this.companyId = companyId;
        this.name = name;
        this.category = category;
        this.quantity = (int) quantity;
        this.incomingPrice = incomingPrice;
        this.incomingDate = incomingDate;
        this.country = country;
        this.naturalStatus = naturalStatus;
    }
}
