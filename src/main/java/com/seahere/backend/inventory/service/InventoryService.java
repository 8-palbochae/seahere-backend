package com.seahere.backend.inventory.service;


import com.seahere.backend.inventory.controller.response.InventoryRespMockDto;
import com.seahere.backend.inventory.entity.InventoryEntity;
import com.seahere.backend.inventory.repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InventoryService {
    private final InventoryRepository inventoryRepository;
    public Slice<InventoryEntity> findAllInventoryBySearch(Long companyId, final Pageable pageable) {
        return inventoryRepository.findAllInventoryBySearch(companyId, pageable);
    }
}