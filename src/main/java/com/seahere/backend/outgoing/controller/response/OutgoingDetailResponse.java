package com.seahere.backend.outgoing.controller.response;

import com.seahere.backend.outgoing.entity.OutgoingDetailEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class OutgoingDetailResponse {
    private Long outgoingId;
    private Long outgoingDetailId;
    private String imgSrc;
    private String productName;
    private float outgoingQuantity;
    private float beforeCount;
    private float afterCount;
    private int price;

    public static OutgoingDetailResponse from(OutgoingDetailEntity outgoingDetail){
        return OutgoingDetailResponse.builder()
                .imgSrc("")
                .productName(outgoingDetail.getProduct().getProductName())
                .outgoingId(outgoingDetail.getOutgoing().getOutgoingId())
                .outgoingDetailId(outgoingDetail.getDetailId())
                .outgoingQuantity(outgoingDetail.getQuantity())
                .price(outgoingDetail.getPrice().intValue())
                .beforeCount(100)
                .afterCount(100 - outgoingDetail.getQuantity())
                .build();
    }
}
