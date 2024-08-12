package com.seahere.backend.sales.controller.response;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Builder
@Getter
public class IncomingWeekRes {
    private LocalDate incomingDate;
    private int incomingPrice;
    private  int week;

}
