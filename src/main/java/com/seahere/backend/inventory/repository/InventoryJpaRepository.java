package com.seahere.backend.inventory.repository;

import com.seahere.backend.inventory.controller.response.InventoryReqDto;
import com.seahere.backend.inventory.entity.InventoryEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface InventoryJpaRepository extends JpaRepository<InventoryEntity, Long> {

    @Query("SELECT new com.seahere.backend.inventory.controller.response.InventoryReqDto(" +
            "i.companyId, i.name, i.category, MAX(i.incomingDate), SUM(i.quantity)) " +
            "FROM InventoryEntity i WHERE i.companyId = :companyId GROUP BY i.name, i.category")
    Page<InventoryReqDto> findPagedInventoryByCompanyId(@Param("companyId") Long companyId, Pageable pageable);
}