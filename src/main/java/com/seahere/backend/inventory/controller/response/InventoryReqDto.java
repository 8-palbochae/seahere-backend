package com.seahere.backend.inventory.controller.response;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Builder
@Getter
public class InventoryReqDto {
    private Long companyId;
    private String name;
    private String category;
    private Date latestIncoming;
    private Long totalQuantity;
}
