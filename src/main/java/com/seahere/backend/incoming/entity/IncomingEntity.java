package com.seahere.backend.incoming.entity;

import com.seahere.backend.company.entity.CompanyEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "incoming")
@NoArgsConstructor
@Getter
public class IncomingEntity {
    @Id @GeneratedValue
    private Long incomingId;
    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn("compa")
    private CompanyEntity company;
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

    @Builder
    public IncomingEntity(Long companyId, Long productId, int quantity, LocalDate incomingDate,
            int incomingPrice, String memo, String countryDetail, String country, String natural, String category, Long userId) {
        this.companyId = companyId;
        this.productId = productId;
        this.quantity = quantity;
        this.incomingDate = incomingDate;
        this.incomingPrice = incomingPrice;
        this.memo = memo;
        this.countryDetail = countryDetail;
        this.country = country;
        this.natural = natural;
        this.category = category;
        this.userId = userId;

    }
}
