package com.seahere.backend.inventory.controller.response;


import lombok.Getter;

import java.util.List;

@Getter
public class InventoryReqListResponse {
    private List<InventoryReqDto> content;
    private final SortResponse sort;
    private final int currentPage;
    private final int size;
    private final boolean first;
    private final boolean last;
    private final boolean hasNext;
}
