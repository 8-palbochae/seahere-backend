package com.seahere.backend.outgoing.controller.response;

import com.seahere.backend.outgoing.entity.OutgoingState;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDate;
@Getter
@ToString
public class OutgoingReqMockDto {
    private Long outgoingId;
    private String customerName;
    private String title;
    private OutgoingState state;
    private String status;
    private LocalDate outgoingDate;
    private boolean partialOutgoing;

    public OutgoingReqMockDto(Long outgoingId, String customerName, String title, OutgoingState state, boolean partialOutgoing) {
        this.outgoingId = outgoingId;
        this.customerName = customerName;
        this.status = state.printState();
        this.title = title;
        this.state = state;
        this.partialOutgoing = partialOutgoing;
        this.outgoingDate = LocalDate.now();
    }

    public OutgoingReqMockDto (Long outgoingId, String customerName, String title, OutgoingState state){
        this(outgoingId,customerName,title,state,false);
    }


    public void setOutgoingStateToReady(){
        this.state = OutgoingState.READY;
        this.status = state.printState();
    }
    public void setOutgoingStateToReject(){
        this.state = OutgoingState.REJECT;
        this.status = state.printState();
    }
}
