package com.seahere.backend.outgoing.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "outgoing_detail")
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Getter
public class OutgoingDetailEntity {
    @Id @GeneratedValue
    private Long detailId;
//    private ProductEntity product
    private String productName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "outgoing_id")
    private OutgoingEntity outgoing;

    private BigDecimal price;

    private float quantity;
}
