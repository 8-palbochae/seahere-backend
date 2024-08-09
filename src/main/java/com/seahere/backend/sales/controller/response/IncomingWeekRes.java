package com.seahere.backend.sales.controller.response;

import com.seahere.backend.sales.dto.IncomingSalesDto;
import lombok.Getter;

import java.util.List;

@Getter
public class IncomingWeekRes {
    private List<IncomingSalesDto> salesList;

}
