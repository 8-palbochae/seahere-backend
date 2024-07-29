package com.seahere.backend.inventory.repository;

import com.seahere.backend.inventory.controller.response.InventoryReqDetailDto;
import com.seahere.backend.inventory.controller.response.InventoryReqDto;
import com.seahere.backend.inventory.entity.InventoryEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface InventoryJpaRepository extends JpaRepository<InventoryEntity, Long> {

    @Query("SELECT new com.seahere.backend.inventory.controller.response.InventoryReqDto(" +
            "i.companyId, i.name, i.category, MAX(i.incomingDate), SUM(i.quantity)) " +
            "FROM InventoryEntity i WHERE i.companyId = :companyId GROUP BY i.name, i.category ORDER BY i.name")
    List<InventoryReqDto> findPagedInventoryByCompanyId(@Param("companyId") Long companyId);

    default Slice<InventoryReqDto> getInventoriesByCompanyId(@Param("companyId") Long companyId, Pageable pageable) {
        List<InventoryReqDto> inventoryList = findPagedInventoryByCompanyId(companyId);
        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), inventoryList.size());
        Page<InventoryReqDto> page = new PageImpl<>(inventoryList.subList(start, end), pageable, inventoryList.size());
        return page;
    }
}
