package com.seahere.backend.alarm.controller.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor
@ToString
public class DiscountInventories {
    private Long inventoryId;
    private String name;
    private String imgUrl;
    private String category;
    private String country;
    private String naturalStatus;
    private int price;
    private int discountPrice;
    private float quantity;
}
