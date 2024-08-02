package com.seahere.backend.incoming.controller.response;

import com.seahere.backend.incoming.dto.IncomingReqDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
public class IncomingHistoryResponse {

    private LocalDate incomingDate;
    private long incomingCount;

    @Builder
    public IncomingHistoryResponse(LocalDate incomingDate, long incomingCount) {
        this.incomingDate = incomingDate;
        this.incomingCount = incomingCount;
    }

    public static IncomingHistoryResponse from(IncomingReqDto incomingReqDto) {
        return IncomingHistoryResponse.builder()
                .incomingDate(incomingReqDto.getIncomingDate())
                .incomingCount(incomingReqDto.getCount())
                .build();
    }
}
