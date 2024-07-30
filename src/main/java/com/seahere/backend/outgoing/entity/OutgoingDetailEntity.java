package com.seahere.backend.outgoing.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

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

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "varchar(255) default 'ACTIVE'")
    private OutgoingDetailState state;

    public void assignOutgoing(OutgoingEntity outgoingEntity){
        this.outgoing = outgoingEntity;
    }

    public void stateToDelete(){
        this.state = OutgoingDetailState.DELETE;
    }
    public void stateToActive(){
        this.state = OutgoingDetailState.ACTIVE;
    }
}
