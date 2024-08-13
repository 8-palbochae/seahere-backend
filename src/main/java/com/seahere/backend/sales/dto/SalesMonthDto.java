package com.seahere.backend.sales.dto;

import lombok.*;


@Getter
@ToString
@Builder
@NoArgsConstructor
public class SalesMonthDto {

    private int incomingPrice;
    private int month;

    public SalesMonthDto(int month, int incomingPrice) {
        this.incomingPrice = incomingPrice;
        this.month = month;
    }
}
