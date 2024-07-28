package com.seahere.backend.inventory.controller.response;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

@Builder
@Getter
public class InventoryReqDto {
    private Long companyId;
    private String name;
    private String category;
    private LocalDate latestIncoming;
    private int totalQuantity;
    private List<InventoryReqDetailDto> products;

    public static InventoryReqDto from(List<InventoryReqDetailDto> list){

        InventoryReqDetailDto firstItem = list.get(0);

        int totalQuantity = list.stream()
                .mapToInt(InventoryReqDetailDto::getQuantity)
                .sum();

        LocalDate latestIncoming = LocalDate.ofEpochDay(list.stream()
                .map(InventoryReqDetailDto::getIncomingDate)
                .max((d1, d2) -> d1.compareTo(d2))
                .orElse(null));

        return InventoryReqDto.builder()
                .companyId(firstItem.getCompanyId())
                .name(firstItem.getName())
                .category(firstItem.getCategory())
                .latestIncoming(latestIncoming)
                .totalQuantity(totalQuantity)
                .products(list)
                .build();
    }
}
