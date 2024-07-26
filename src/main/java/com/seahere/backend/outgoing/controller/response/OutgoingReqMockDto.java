package com.seahere.backend.outgoing.controller.response;

import lombok.Getter;

import java.time.LocalDate;
@Getter
public class OutgoingReqMockDto {
    private Long outgoingId;
    private String customerName;
    private String title;
    private String status;
    private LocalDate outgoingDate;

    public OutgoingReqMockDto(Long outgoingId, String customerName, String title, String status) {
        this.outgoingId = outgoingId;
        this.customerName = customerName;
        this.title = title;
        this.status = status;
        this.outgoingDate = LocalDate.now();
    }
}
