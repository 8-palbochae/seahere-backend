package com.seahere.backend.inventory.controller.response;

import lombok.*;

import java.time.LocalDate;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class InventoryReqDto {
    private Long companyId;
    private String name;
    private String category;
    private LocalDate latestIncoming;
    private Long totalQuantity;
}
