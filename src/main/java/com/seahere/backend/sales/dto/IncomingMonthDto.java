package com.seahere.backend.sales.dto;

import lombok.*;

import java.time.LocalDate;


@Getter
@ToString
@Builder
@NoArgsConstructor
public class IncomingMonthDto {

    private int incomingPrice;
    private int month;

    public IncomingMonthDto(int month, int incomingPrice) {
        this.incomingPrice = incomingPrice;
        this.month = month;
    }
}
