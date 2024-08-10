package com.seahere.backend.sales.dto;

import lombok.*;

import java.time.LocalDate;


@AllArgsConstructor
@Getter
@ToString
@Builder
@NoArgsConstructor
public class IncomingSalesDto {

    private LocalDate incomingDate;
    private int week;
    private int incomingPrice;

    public IncomingSalesDto(String incomingDate, Integer week, Integer incomingPrice) {
        this.incomingDate = LocalDate.parse(incomingDate);
        this.week = week;
        this.incomingPrice = incomingPrice;
    }
}

