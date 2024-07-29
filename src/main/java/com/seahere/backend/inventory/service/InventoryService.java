package com.seahere.backend.inventory.service;

import com.seahere.backend.inventory.controller.response.InventoryReqDto;
import com.seahere.backend.inventory.repository.InventoryJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InventoryService {
    private final InventoryJpaRepository inventoryJpaRepository;

    public Slice<InventoryReqDto> getInventoryByCompanyId(Long companyId, Pageable pageable, String search) {
        return inventoryJpaRepository.getInventoriesByCompanyId(companyId, pageable);
    }
}
