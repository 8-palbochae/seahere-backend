package com.seahere.backend.inventory.controller.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InventoryReqSearchRequest {
    private Long companyId;
    private int size;
    private int page;
    private String search;
}
