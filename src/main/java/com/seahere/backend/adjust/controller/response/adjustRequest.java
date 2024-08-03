package com.seahere.backend.adjust.controller.response;
import java.time.LocalDate;

public class adjustRequest {
    private Long adjustId;
    private LocalDate adjustDate;
    private String reason;
    private int beforeQuantity;
    private int afterQuantity;
    private Long inventoryId;
}
