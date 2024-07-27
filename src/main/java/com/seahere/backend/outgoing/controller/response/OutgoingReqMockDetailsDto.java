package com.seahere.backend.outgoing.controller.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@ToString
public class OutgoingReqMockDetailsDto {

    private Long outgoingId;
    private Long outgoingDetailId;
    private String imgSrc;
    private String productName;
    private int outgoingQuantity;
    private int beforeCount;
    private int afterCount;
    private int price;
}
