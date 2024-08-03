package com.seahere.backend.history.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.seahere.backend.history.dto.HistoryListDto;
import com.seahere.backend.incoming.entity.QIncomingEntity;
import com.seahere.backend.inventory.entity.QInventoryEntity;
import com.seahere.backend.outgoing.entity.QOutgoingEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.el.Expression;
import java.time.LocalDate;
import java.util.List;

import static com.seahere.backend.incoming.entity.QIncomingEntity.incomingEntity;
import static com.seahere.backend.inventory.entity.QInventoryEntity.inventoryEntity;
import static com.seahere.backend.outgoing.entity.QOutgoingEntity.outgoingEntity;

@RequiredArgsConstructor
@Repository
public class HistoryRepository {

    private final JPAQueryFactory queryFactory;

    public List<HistoryListDto> findByHistoryDate(LocalDate startDate, LocalDate endDate){
        queryFactory.select(Projections.constructor(HistoryListDto.class,
                incomingEntity.incomingDate.as("date"),
                incomingEntity.incomingId.count().as("incomingCount"),
                Expressions.constant(0L).
                ))
    }
}
