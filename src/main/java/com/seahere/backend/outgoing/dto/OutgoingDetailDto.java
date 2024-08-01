package com.seahere.backend.outgoing.dto;

import com.seahere.backend.outgoing.controller.response.OutgoingDetailResponse;
import com.seahere.backend.outgoing.entity.OutgoingDetailEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Getter
@AllArgsConstructor
@Builder
@Setter
public class OutgoingDetailDto {
    private Long outgoingId;
    private Long outgoingDetailId;
    private String imgSrc;
    private String productName;
    private float outgoingQuantity;
    private int price;
    private float inventoryCount;

    public static OutgoingDetailDto from(OutgoingDetailEntity outgoingDetail){
        return OutgoingDetailDto.builder()
                .imgSrc("")
                .productName(outgoingDetail.getProduct().getProductName())
                .outgoingId(outgoingDetail.getOutgoing().getOutgoingId())
                .outgoingDetailId(outgoingDetail.getDetailId())
                .outgoingQuantity(outgoingDetail.getQuantity())
                .price(outgoingDetail.getPrice().intValue())
                .build();
    }


}
