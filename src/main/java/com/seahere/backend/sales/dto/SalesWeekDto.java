package com.seahere.backend.sales.dto;

import lombok.*;

import java.time.LocalDate;


@AllArgsConstructor
@Getter
@ToString
@Builder
@NoArgsConstructor
public class SalesWeekDto {

    private LocalDate commonDate;
    private int week;
    private int commonPrice;

    public SalesWeekDto(String incomingDate, Integer week, Integer incomingPrice) {
        this.commonDate = LocalDate.parse(incomingDate);
        this.week = week;
        this.commonPrice = incomingPrice;
    }
}

