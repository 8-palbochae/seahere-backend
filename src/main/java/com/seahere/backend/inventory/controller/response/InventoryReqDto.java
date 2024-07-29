package com.seahere.backend.inventory.controller.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class InventoryReqDto {
    private Long companyId;
    private String name;
    private String category;
    private LocalDate latestIncoming;
    private Long totalQuantity;
}
