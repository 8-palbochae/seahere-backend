package com.seahere.backend.outgoing.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "outgoing")
@NoArgsConstructor
@Getter
public class OutgoingEntity {
    @Id @GeneratedValue
    private Long outgoingId;

    private Long companyId;

    private String customerName;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "outgoing")
    private final List<OutgoingDetailEntity> outgoingDetails = new ArrayList<>();

    private LocalDate outgoingDate;

    @Enumerated(EnumType.STRING)
    private OutgoingState outgoingState;

    private boolean partialOutgoing;
    //    private float quantity;
    //    private UserEntity customer;

    @Builder
    public OutgoingEntity(Long companyId,String customerName, LocalDate outgoingDate, OutgoingState outgoingState, boolean partialOutgoing) {
        this.companyId = companyId;
        this.customerName = customerName;
        this.outgoingDate = outgoingDate;
        this.outgoingState = outgoingState;
        this.partialOutgoing = partialOutgoing;
    }

    public void addOutgoingDetail(OutgoingDetailEntity outgoingDetail){
        this.outgoingDetails.add(outgoingDetail);
        outgoingDetail.assignOutgoing(this);
    }
}
