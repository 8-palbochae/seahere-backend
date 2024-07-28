package com.seahere.backend.inventory.service;


import com.seahere.backend.inventory.controller.response.InventoryReqDto;
import com.seahere.backend.inventory.entity.InventoryEntity;
import com.seahere.backend.inventory.repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InventoryService {
    private final InventoryRepository inventoryRepository;
    public Slice<InventoryReqDto> findAllInventoryBySearch(Long companyId, Pageable pageable, String search) {
        return inventoryRepository.findAllInventoryBySearch(companyId, pageable, search);
    }
}