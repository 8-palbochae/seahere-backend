package com.seahere.backend.incoming.dto;


import com.seahere.backend.incoming.entity.IncomingEntity;
import com.seahere.backend.outgoing.dto.OutgoingReqDto;
import com.seahere.backend.outgoing.entity.OutgoingEntity;
import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@Getter
@ToString
@Builder
@NoArgsConstructor
public class IncomingReqDto {

    private LocalDate incomingDate;
    private long count;

}
