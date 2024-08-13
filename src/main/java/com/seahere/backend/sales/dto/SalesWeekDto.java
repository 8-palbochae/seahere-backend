package com.seahere.backend.sales.dto;

import lombok.*;

import java.time.LocalDate;


@AllArgsConstructor
@Getter
@ToString
@Builder
@NoArgsConstructor
public class SalesWeekDto {

    private LocalDate incomingDate;
    private int week;
    private int incomingPrice;

    public SalesWeekDto(String incomingDate, Integer week, Integer incomingPrice) {
        this.incomingDate = LocalDate.parse(incomingDate);
        this.week = week;
        this.incomingPrice = incomingPrice;
    }
}

