package com.seahere.backend.outgoing.controller.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@NoArgsConstructor
public class OutgoingCreateReq {
    private Long companyId;
    private boolean partialOutgoing;
    private List<OutgoingCreateDetailReq> details;

    @Builder
    public OutgoingCreateReq(Long companyId, boolean partialOutgoing, List<OutgoingCreateDetailReq> details) {
        this.companyId = companyId;
        this.partialOutgoing = partialOutgoing;
        this.details = details;
    }
}
