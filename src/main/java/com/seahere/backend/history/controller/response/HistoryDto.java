package com.seahere.backend.history.controller.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class HistoryDto {
    private int incomingCount;
    private int outgoingCount;
    private int modifiedCount;

    @Builder
    public HistoryDto(LocalDate modifiedDate, int incomingCount, int outgoingCount, int modifiedCount) {
        this.incomingCount = incomingCount;
        this.outgoingCount = outgoingCount;
        this.modifiedCount = modifiedCount;
    }
}
