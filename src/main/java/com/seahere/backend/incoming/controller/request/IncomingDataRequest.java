package com.seahere.backend.incoming.controller.request;

import com.seahere.backend.incoming.dto.IncomingMockDto;
import com.seahere.backend.product.controller.response.IncomingSearchResponse;
import com.seahere.backend.product.dto.ProductMockDto;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Builder
@Getter
@ToString
public class IncomingDataRequest {
    private final Long productId;
    private final int quantity;
    private final int incomingPrice;
    private final String memo;
    private final String countryDetail;
    private final String natural;
    private final String category;
    private final String country;

}
