package com.seahere.backend.incoming.entity;

import com.seahere.backend.company.entity.CompanyEntity;
import com.seahere.backend.user.domain.UserEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

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
    @JoinColumn(name = "company_id")
    private CompanyEntity company;

    private Long productId;
    private int quantity;

    private LocalDate incomingDate;

//    private LocalDate incomingDate;
    private int incomingPrice;
    private String memo;
    private String countryDetail;
    private String country;
    private String naturalStatus;
    private String category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserEntity user;

    public void enroll(UserEntity user, CompanyEntity company){
        this.user = user;
        this.company = company;
    }

    @Builder
    public IncomingEntity(Long incomingId, CompanyEntity company, Long productId, int quantity, LocalDate incomingDate, int incomingPrice, String memo, String countryDetail, String country, String naturalStatus, String category, UserEntity user) {
        this.incomingId = incomingId;
        this.company = company;
        this.productId = productId;
        this.quantity = quantity;
        this.incomingDate = incomingDate;
        this.incomingPrice = incomingPrice;
        this.memo = memo;
        this.countryDetail = countryDetail;
        this.country = country;
        this.naturalStatus = naturalStatus;
        this.category = category;
        this.user = user;
    }
}
