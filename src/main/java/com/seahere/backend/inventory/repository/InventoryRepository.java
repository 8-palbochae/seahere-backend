package com.seahere.backend.inventory.repository;


import com.seahere.backend.inventory.entity.InventoryEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class InventoryRepository {

    public Slice<InventoryEntity> findAllInventoryBySearch( Long companyId, Pageable pageable) {
    }
}