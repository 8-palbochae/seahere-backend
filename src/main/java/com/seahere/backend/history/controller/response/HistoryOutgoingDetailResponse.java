package com.seahere.backend.history.controller.response;

import com.seahere.backend.outgoing.entity.OutgoingDetailEntity;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class HistoryOutgoingDetailResponse {
    private Long outgoingId;
    private Long outgoingDetailId;
    private String imgSrc;
    private String productName;
    private float outgoingQuantity;
    private int price;
    private String category;
    private String country;
    private String naturalStatus;
    public static HistoryOutgoingDetailResponse from(OutgoingDetailEntity outgoingDetail){
        return HistoryOutgoingDetailResponse.builder()
                .imgSrc(outgoingDetail.getProduct().getProductImg())
                .outgoingId(outgoingDetail.getOutgoing().getOutgoingId())
                .price(outgoingDetail.getPrice().intValue())
                .category(outgoingDetail.getCategory())
                .country(outgoingDetail.getCountry())
                .naturalStatus(outgoingDetail.getNaturalStatus())
                .outgoingQuantity(outgoingDetail.getQuantity())
                .productName(outgoingDetail.getProduct().getProductName())
                .outgoingDetailId(outgoingDetail.getDetailId()).build();
    }
}
