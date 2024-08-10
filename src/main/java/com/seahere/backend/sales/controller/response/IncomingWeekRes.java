package com.seahere.backend.sales.controller.response;

import com.seahere.backend.sales.dto.IncomingSalesDto;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

@Builder
@Getter
public class IncomingWeekRes {
    private LocalDate incomingDate;
    private int incomingPrice;
    private  int week;

}
