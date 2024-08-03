package com.seahere.backend.history.controller.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
public class HistoryGetReq {
    private LocalDate startDate;
    private LocalDate endDate;

    @Builder
    public HistoryGetReq(LocalDate startDate, LocalDate endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }
}
