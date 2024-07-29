package com.seahere.backend.inventory.controller.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class InventoryReqSearchRequest {
    private Long companyId;
    private int size = 10;
    private int page = 0;
    private String search = "";
}
