package com.seahere.backend.incoming.dto;


import com.seahere.backend.incoming.entity.IncomingEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDate;

@AllArgsConstructor
@Getter
@ToString
@Builder
public class IncomingReqDto {

    private Long incomingId;
    private Long companyId;
    private Long productId;
    private int quantity;
    private LocalDate incomingDate;
    private int incomingPrice;
    private String memo;
    private String countryDetail;
    private String country;
    private String naturalStatus;
    private String category;
    private Long userId;
    private String productImg;

    public static IncomingReqDto from(IncomingEntity incomingEntity) {
        return IncomingReqDto.builder()
                .companyId(incomingEntity.getCompany().getId())
                .incomingId(incomingEntity.getIncomingId())
                .productId(incomingEntity.getProduct().getProductId())
                .category(incomingEntity.getCategory())
                .incomingPrice(incomingEntity.getIncomingPrice())
                .incomingDate(incomingEntity.getIncomingDate())
                .productImg(incomingEntity.getProduct().getProductImg())
                .build();
    }


}
