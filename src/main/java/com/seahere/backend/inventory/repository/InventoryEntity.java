package com.seahere.backend.inventory.repository;

import lombok.Getter;
import java.util.Date;

@Getter
public class InventoryEntity {
    private int inventoryId;
    private int inventoryQuantity;
    private int price;
    private Date incomeDate;
}