package com.seahere.backend.inventory.controller.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;

@ToString
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class InventoryReqDto {
    private Long companyId;
    private String name;
    private String category;
    private LocalDate latestIncoming;
    private Long totalQuantity;
}
