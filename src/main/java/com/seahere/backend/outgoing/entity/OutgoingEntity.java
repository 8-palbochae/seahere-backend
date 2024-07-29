package com.seahere.backend.outgoing.entity;

import com.seahere.backend.company.entity.CompanyEntity;
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

    @ManyToOne
    @JoinColumn(name = "company_id")
    private CompanyEntity company;

    private String customerName;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "outgoing")
    private final List<OutgoingDetailEntity> outgoingDetails = new ArrayList<>();

    private LocalDate outgoingDate;

    @Enumerated(EnumType.STRING)
    private OutgoingState outgoingState;

    private boolean partialOutgoing;
    //    private UserEntity customer;

    @Builder
    public OutgoingEntity(CompanyEntity company,String customerName, LocalDate outgoingDate, OutgoingState outgoingState, boolean partialOutgoing) {
        this.company = company;
        this.customerName = customerName;
        this.outgoingDate = outgoingDate;
        this.outgoingState = outgoingState;
        this.partialOutgoing = partialOutgoing;
    }

    public void addOutgoingDetail(OutgoingDetailEntity outgoingDetail){
        this.outgoingDetails.add(outgoingDetail);
        outgoingDetail.assignOutgoing(this);
    }

    public void changeState(OutgoingState state){
        this.outgoingState = state;
    }
}
