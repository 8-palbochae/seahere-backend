package com.seahere.backend.history.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.seahere.backend.history.dto.HistoryListDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.*;

import static com.seahere.backend.adjust.entity.QAdjustEntity.adjustEntity;
import static com.seahere.backend.incoming.entity.QIncomingEntity.incomingEntity;
import static com.seahere.backend.outgoing.entity.QOutgoingEntity.outgoingEntity;

@RequiredArgsConstructor
@Repository
@Slf4j
public class HistoryRepository {

    private final JPAQueryFactory queryFactory;

    public List<HistoryListDto> findByHistoryDate(LocalDate startDate, LocalDate endDate) {
        Map<LocalDate, HistoryListDto> map = new HashMap<>();

        List<HistoryListDto> incomingResults = queryFactory
                .select(Projections.bean(HistoryListDto.class,
                        incomingEntity.incomingDate.as("date"),
                        incomingEntity.incomingId.count().as("incomingCount"),
                        Expressions.ZERO.longValue().as("outgoingCount"),
                        Expressions.ZERO.longValue().as("adjustCount")))
                .from(incomingEntity)
                .where(incomingEntity.incomingDate.goe(startDate).and(incomingEntity.incomingDate.loe(endDate)))
                .groupBy(incomingEntity.incomingDate)
                .fetch();

        for (HistoryListDto dto : incomingResults) {
            map.put(dto.getDate(), dto);
        }

        List<HistoryListDto> outgoingResults = queryFactory
                .select(Projections.bean(HistoryListDto.class,
                        outgoingEntity.outgoingDate.as("date"),
                        Expressions.ZERO.longValue().as("incomingCount"),
                        outgoingEntity.outgoingId.count().as("outgoingCount"),
                        Expressions.ZERO.longValue().as("adjustCount")))
                .from(outgoingEntity)
                .where(outgoingEntity.outgoingDate.goe(startDate).and(outgoingEntity.outgoingDate.loe(endDate)))
                .groupBy(outgoingEntity.outgoingDate)
                .fetch();

        for (HistoryListDto dto : outgoingResults) {
            if (map.containsKey(dto.getDate())) {
                HistoryListDto historyListDto = map.get(dto.getDate());
                historyListDto.setOutgoingCount(dto.getOutgoingCount());
                continue;
            }
            map.put(dto.getDate(), dto);
        }


        List<HistoryListDto> adjustResults = queryFactory
                .select(Projections.bean(HistoryListDto.class,
                        adjustEntity.adjustDate.as("date"),
                        Expressions.ZERO.longValue().as("incomingCount"),
                        Expressions.ZERO.longValue().as("outgoingCount"),
                        adjustEntity.adjustId.count().as("adjustCount")))
                .from(adjustEntity)
                .where(adjustEntity.adjustDate.goe(startDate).and(adjustEntity.adjustDate.loe(endDate)))
                .groupBy(adjustEntity.adjustDate)
                .fetch();

        for (HistoryListDto dto : adjustResults) {
            if (map.containsKey(dto.getDate())) {
                HistoryListDto historyListDto = map.get(dto.getDate());
                historyListDto.setAdjustCount(dto.getAdjustCount());
            }
        }
        ArrayList<HistoryListDto> results = new ArrayList<>(map.values());
        results.sort(Comparator.comparing(HistoryListDto::getDate));
        return results;
    }
}
