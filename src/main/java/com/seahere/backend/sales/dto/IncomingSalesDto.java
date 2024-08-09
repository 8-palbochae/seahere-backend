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
    private int incomingPrice;
    private int week;
}
