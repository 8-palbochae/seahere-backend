package com.seahere.backend.outgoing.controller.response;

import com.seahere.backend.outgoing.dto.OutgoingDetailDto;
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
    private String productImg;
    private String productName;
    private float outgoingQuantity;
    private float beforeCount;
    private float afterCount;
    private int price;

    public static OutgoingDetailResponse from(OutgoingDetailDto outgoingDetailDto){
        return OutgoingDetailResponse.builder()
                .productImg(outgoingDetailDto.getImgSrc())
                .outgoingId(outgoingDetailDto.getOutgoingId())
                .outgoingDetailId(outgoingDetailDto.getOutgoingDetailId())
                .outgoingQuantity(outgoingDetailDto.getOutgoingQuantity())
                .beforeCount(outgoingDetailDto.getInventoryQuantity())
                .afterCount(outgoingDetailDto.getInventoryQuantity() - outgoingDetailDto.getOutgoingQuantity())
                .price(outgoingDetailDto.getPrice().intValue())
                .productName(outgoingDetailDto.getProductName())
                .build();
    }
}
