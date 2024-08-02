package com.seahere.backend.incoming.controller.request;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Getter
@Setter
public class IncomingDateRequest {

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate incomingDate = LocalDate.now();


}
