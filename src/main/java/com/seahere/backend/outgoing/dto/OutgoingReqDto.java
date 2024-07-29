package com.seahere.backend.outgoing.dto;

import com.seahere.backend.outgoing.entity.OutgoingDetailEntity;
import com.seahere.backend.outgoing.entity.OutgoingEntity;
import com.seahere.backend.outgoing.entity.OutgoingState;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Builder
public class OutgoingReqDto {

    private Long outgoingId;
    private Long companyId;
    private String customerName;
    private LocalDate outgoingDate;
    private OutgoingState state;
    private String status;
    private boolean partialOutgoing;
    private String title;

    public static OutgoingReqDto from(OutgoingEntity outgoingEntity) {
        return OutgoingReqDto.builder()
                .companyId(outgoingEntity.getCompany().getId())
                .customerName(outgoingEntity.getCustomerName())
                .outgoingDate(outgoingEntity.getOutgoingDate())
                .state(outgoingEntity.getOutgoingState())
                .status(outgoingEntity.getOutgoingState().printState())
                .partialOutgoing(outgoingEntity.isPartialOutgoing())
                .title(calcTitle(outgoingEntity.getOutgoingDetails()))
                .outgoingId(outgoingEntity.getOutgoingId()).build();
    }

    public static String calcTitle(List<OutgoingDetailEntity> details){
        if(details.isEmpty()) return "상품이 없습니다.";
        if(details.size() == 1) return details.get(0).getProductName() + "외0건";
        return details.get(0).getProductName() + "외"+(details.size()-1)+"건";
    }
}