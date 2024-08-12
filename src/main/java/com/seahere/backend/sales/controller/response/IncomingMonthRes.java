package com.seahere.backend.sales.controller.response;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.time.Month;

@Builder
@Getter
public class IncomingMonthRes {

    private int month;
    private int incomingPrice;

}
