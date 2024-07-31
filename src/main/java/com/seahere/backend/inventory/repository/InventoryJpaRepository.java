package com.seahere.backend.inventory.repository;

import com.seahere.backend.inventory.controller.response.InventoryReqDetailDto;
import com.seahere.backend.inventory.controller.response.InventoryReqDto;
import com.seahere.backend.inventory.entity.InventoryEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface InventoryJpaRepository extends JpaRepository<InventoryEntity, Long> {

//    @Query("SELECT new com.seahere.backend.inventory.controller.response.InventoryReqDto(" +
//            "i.company.id, i.name, i.category, MAX(i.incomingDate), SUM(i.quantity)) " +
//            "FROM InventoryEntity i WHERE i.company.id = :companyId AND i.name LIKE %:search% GROUP BY i.name, i.category")
//    Page<InventoryReqDto> findPagedInventoryByCompanyId(@Param("companyId") Long companyId,
//                                                        @Param("search") String search,
//                                                        Pageable pageable);

//    @Query("SELECT new com.seahere.backend.inventory.controller.response.InventoryReqDetailDto(" +
//            "i.inventoryId, i.company.id, i.name, i.category, i.quantity, i.incomingDate, i.country, i.naturalStatus) " +
//            "FROM InventoryEntity i WHERE i.company.id = :companyId AND i.name = :name AND i.category = :category")
//    Page<InventoryReqDetailDto> findPagedProductsByCompanyId(@Param("companyId") Long companyId,
//                                                             @Param("name") String name,
//                                                             @Param("category") String category,
//                                                             Pageable pageable);
}
