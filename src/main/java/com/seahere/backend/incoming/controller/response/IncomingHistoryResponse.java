package com.seahere.backend.incoming.controller.response;

import com.seahere.backend.incoming.dto.IncomingCountDto;
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

    public static IncomingHistoryResponse from(IncomingCountDto incomingCountDto) {
        return IncomingHistoryResponse.builder()
                .incomingDate(incomingCountDto.getIncomingDate())
                .incomingCount(incomingCountDto.getCount())
                .build();
    }
}
