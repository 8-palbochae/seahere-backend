package com.seahere.backend.inventory.service;

import com.seahere.backend.inventory.controller.request.InventoryEditReq;
import com.seahere.backend.inventory.entity.InventoryDetailEntity;
import com.seahere.backend.inventory.entity.InventoryEntity;
import com.seahere.backend.inventory.exception.InventoryNotFoundException;
import com.seahere.backend.inventory.repository.InventoryJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class InventoryDetailService {
    private final InventoryJpaRepository inventoryJpaRepository;

    public void editInventoryDetail(Long inventoryId,InventoryEditReq inventoryEditReq){
        InventoryEntity inventory = inventoryJpaRepository.findById(inventoryId)
                .orElseThrow(InventoryNotFoundException::new);

        InventoryDetailEntity inventoryDetail = inventory.getInventoryDetail();
        inventoryDetail.edit(inventoryEditReq);

        inventoryJpaRepository.save(inventory);
    }
}
