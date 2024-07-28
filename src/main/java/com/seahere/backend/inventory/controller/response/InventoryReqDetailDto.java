package com.seahere.backend.inventory.controller.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@ToString
public class InventoryReqDetailDto {
    private Long companyId;
    private String name;
    private String country;
    private String category;
    private String natural;
    private String incomingCompany;
    private int quantity;
    private int incomingPrice;
    private int incomingDate;
}
