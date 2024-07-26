package com.seahere.backend.inventory.repository;

import lombok.Getter;

import java.util.Date;
import java.util.List;

@Getter
public class ProductsEntity {
    private int productsId;
    private String productName;
    private String country;
    private String category;
    private String natural;
    private int totalQuantity;
    private int page;
    private int size;
    private int totalPage;
    private int totalCount;
    private Date latestIncoming;
    private List<InventoryEntity> detailData;
}
