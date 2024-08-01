package com.seahere.backend.outgoing.dto;

import lombok.*;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
@Builder
@Setter
@NoArgsConstructor
@ToString
public class OutgoingDetailDto {
    private Long outgoingId;
    private Long outgoingDetailId;
    private String imgSrc;
    private String productName;
    private float outgoingQuantity;
    private BigDecimal price;
    private float inventoryQuantity;

}
