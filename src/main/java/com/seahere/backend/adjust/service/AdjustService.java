package com.seahere.backend.adjust.service;

import com.seahere.backend.adjust.controller.request.AdjustRequest;
import com.seahere.backend.adjust.entity.AdjustEntity;
import com.seahere.backend.adjust.repository.AdjustJpaRepository;
import com.seahere.backend.inventory.entity.InventoryEntity;
import com.seahere.backend.inventory.exception.InventoryNotFoundException;
import com.seahere.backend.inventory.repository.InventoryJpaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class AdjustService {
    private final AdjustJpaRepository adjustJpaRepository;
    private final InventoryJpaRepository inventoryJpaRepository;

    public void save(AdjustRequest adjustRequest) {
        InventoryEntity inventoryEntity = inventoryJpaRepository.findById(adjustRequest.getInventoryId())
                .orElseThrow(InventoryNotFoundException::new);

        AdjustEntity adjustEntity = adjustRequest.toEntity(inventoryEntity);
        inventoryEntity.updateQuantity(adjustRequest.getAfterQuantity());
        adjustJpaRepository.save(adjustEntity);
    }
}