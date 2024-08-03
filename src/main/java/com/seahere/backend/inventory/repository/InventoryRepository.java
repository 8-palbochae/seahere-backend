package com.seahere.backend.inventory.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.seahere.backend.inventory.controller.response.InventoryDetailResponse;
import com.seahere.backend.inventory.controller.response.InventoryResponse;
import com.seahere.backend.inventory.entity.QInventoryEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class InventoryRepository {

    private final JPAQueryFactory queryFactory;

    private static final QInventoryEntity inventory = QInventoryEntity.inventoryEntity;

    public Slice<InventoryResponse> findPagedInventoryByCompanyId(Long companyId, String search, Pageable pageable) {
        List<InventoryResponse> results = queryFactory
                .select(Projections.constructor(InventoryResponse.class,
                        inventory.company.id,
                        inventory.product.productName.as("name"),
                        inventory.category,
                        inventory.incomingDate.max().as("latestIncoming"),
                        inventory.quantity.sum().as("totalQuantity").floatValue()))
                .from(inventory)
                .where(inventory.company.id.eq(companyId)
                        .and(inventory.product.productName.containsIgnoreCase(search)))
                .groupBy(inventory.product.productName, inventory.category, inventory.company.id)
                .orderBy(inventory.product.productName.asc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize() + 1)
                .fetch();

        boolean hasNext = results.size() > pageable.getPageSize();
        if (hasNext) {
            results = results.subList(0, pageable.getPageSize());
        }

        return new SliceImpl<>(results, pageable, hasNext);
    }

    public Slice<InventoryDetailResponse> findPagedProductsByCompanyId(Long companyId, String name, String category, Pageable pageable) {
        List<InventoryDetailResponse> results = queryFactory
                .select(Projections.constructor(InventoryDetailResponse.class,
                        inventory.inventoryId,
                        inventory.company.id,
                        inventory.product.productName.as("name"),
                        inventory.category,
                        inventory.quantity.floatValue(),
                        inventory.incomingDate,
                        inventory.country,
                        inventory.naturalStatus))
                .from(inventory)
                .where(inventory.company.id.eq(companyId)
                        .and(inventory.product.productName.eq(name))
                        .and(inventory.category.eq(category)))
                .orderBy(inventory.incomingDate.asc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize() + 1)
                .fetch();

        boolean hasNext = results.size() > pageable.getPageSize();
        if (hasNext) {
            results = results.subList(0, pageable.getPageSize());
        }

        return new SliceImpl<>(results, pageable, hasNext);
    }
}
