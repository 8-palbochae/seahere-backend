package com.seahere.backend.inventory.controller.response;

import com.seahere.backend.common.response.SortResponse;
import lombok.Getter;
import org.springframework.data.domain.Slice;

@Getter
public class InventoryReqListResponse {
    private final Slice<InventoryResponse> content;
    private final SortResponse sort;
    private final int currentPage;
    private final int size;
    private final boolean first;
    private final boolean last;
    private final boolean hasNext;

    public InventoryReqListResponse(Slice<InventoryResponse> slice) {
        this.content = slice; // 이미 InventoryReqDto 객체들이므로 변환 필요 없음
        this.sort = new SortResponse(slice.getSort());
        this.currentPage = slice.getNumber();
        this.size = slice.getSize();
        this.first = slice.isFirst();
        this.last = slice.isLast();
        this.hasNext = slice.hasNext();
    }
}
