package com.seahere.backend.inventory.controller.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.util.Date;
import java.util.List;

@Getter
@ToString
@AllArgsConstructor
public class InventoryRespMockDto {
    private String name;
    private String category;
    private int totalQuantity;
    private Date latestIncoming;
    private List<InventoryRespMockDetailDto> productEntityList;
}
