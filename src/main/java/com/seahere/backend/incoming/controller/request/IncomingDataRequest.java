package com.seahere.backend.incoming.controller.request;

import com.seahere.backend.incoming.entity.IncomingEntity;
import com.seahere.backend.product.entity.ProductEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDate;

@Builder
@Getter
@ToString
public class IncomingDataRequest {
    private final Long productId;
    private final Float quantity;
    private final int incomingPrice;
    private final String memo;
    private final String countryDetail;
    private final String naturalStatus;
    private final String category;
    private final String country;

    public IncomingEntity toEntity(ProductEntity product) {
        return IncomingEntity.builder()
                .product(product)
                .quantity(this.quantity)
                .incomingDate(LocalDate.now())
                .incomingPrice(this.incomingPrice)
                .memo(this.memo)
                .category(this.category)
                .country(this.country)
                .countryDetail(this.countryDetail)
                .naturalStatus(this.naturalStatus)
                .build();
    }
}
