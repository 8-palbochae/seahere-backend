package com.seahere.backend.inventory.controller.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InventoryReqDetailSearchRequest {
    private Long companyId;
    @Builder.Default
    private int size = 10;
    @Builder.Default
    private int page = 0;
    private String name;
    private String category;
}
