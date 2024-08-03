package com.seahere.backend.history.controller.response;

import com.seahere.backend.history.dto.HistoryListDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class HistoryResponse {
    LocalDate date;
    private int incomingCount;
    private int outgoingCount;
    private int modifiedCount;

    @Builder
    public HistoryResponse(LocalDate date, int incomingCount, int outgoingCount, int modifiedCount) {
        this.date = date;
        this.incomingCount = incomingCount;
        this.outgoingCount = outgoingCount;
        this.modifiedCount = modifiedCount;
    }

    public static HistoryResponse from(HistoryListDto historyListDto){
        return HistoryResponse.builder()
                .date(historyListDto.getDate())
                .incomingCount(historyListDto.getIncomingCount())
                .modifiedCount(historyListDto.getModifiedCount())
                .outgoingCount(historyListDto.getOutgoingCount())
                .build();
    }
}
