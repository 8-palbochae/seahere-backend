package com.seahere.backend.incoming.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDate;

@AllArgsConstructor
@Getter
@ToString
public class IncomingMockDto {

    private Long incomingId;
    private Long companyId;
    private Long productId;
    private int quantity;
    private LocalDate incomingDate;
    private int incomingPrice;
    private String memo;
    private String countryDetail;
    private String country;
    private String natural;
    private String category;
    private Long userId;

}
