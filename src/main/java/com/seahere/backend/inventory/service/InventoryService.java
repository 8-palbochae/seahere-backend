package com.seahere.backend.inventory.service;

import com.seahere.backend.inventory.controller.response.InventoryReqDetailDto;
import com.seahere.backend.inventory.controller.response.InventoryReqDto;
import com.seahere.backend.inventory.repository.InventoryJpaRepository;
import com.seahere.backend.inventory.repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InventoryService {
    private final InventoryRepository inventoryRepository;

    public Slice<InventoryReqDto> findPagedInventoryByCompanyId(Long companyId, Pageable pageable, String search) {
        return inventoryRepository.findPagedInventoryByCompanyId(companyId, search, pageable);
    }

    public Slice<InventoryReqDetailDto> findPagedProductsByCompanyId(Long companyId, String name, String category, PageRequest pageable) {
        return inventoryRepository.findPagedProductsByCompanyId(companyId, name, category, pageable);
    }
}
