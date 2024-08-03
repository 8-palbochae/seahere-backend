package com.seahere.backend.history.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Getter
@NoArgsConstructor
public class HistoryListDto {
    LocalDate date;
    private int incomingCount;
    private int outgoingCount;
    private int modifiedCount;

    @Builder
    public HistoryListDto(LocalDate date, int incomingCount, int outgoingCount, int modifiedCount) {
        this.date = date;
        this.incomingCount = incomingCount;
        this.outgoingCount = outgoingCount;
        this.modifiedCount = modifiedCount;
    }
}
