package com.seahere.backend.history.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.seahere.backend.history.dto.HistoryListDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.seahere.backend.adjust.entity.QAdjustEntity.adjustEntity;
import static com.seahere.backend.incoming.entity.QIncomingEntity.incomingEntity;
import static com.seahere.backend.outgoing.entity.QOutgoingEntity.outgoingEntity;

@RequiredArgsConstructor
@Repository
@Slf4j
public class HistoryRepository {

    private final JPAQueryFactory queryFactory;

    public List<HistoryListDto> findByHistoryDate(LocalDate startDate, LocalDate endDate) {
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

        for(HistoryListDto dto : incomingResults){
            log.info("incomingResults = {}",dto.getIncomingCount());
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

        for(HistoryListDto dto : outgoingResults){
            log.info("outgoingResults = {}",dto.getOutgoingCount());
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

        for(HistoryListDto dto : outgoingResults){
            log.info("adjustResults = {}",dto.getAdjustCount());
        }


            //map 키벨류로 값을 넣고 키가 있다면 해당 키에 값 삽입하고 아니면 자기 값만 넣고 나머지 00




//        List<HistoryListDto> result = Stream.of(incomingResults, outgoingResults, adjustResults)
//                .flatMap(List::stream)
//                .filter(dto -> dto.getDate() != null)
//                .collect(Collectors.groupingBy(HistoryListDto::getDate))
//                .entrySet().stream()
//                .map(entry -> {
//                    String date = String.valueOf(entry.getKey());
//                    List<HistoryListDto> values = entry.getValue();
//                    long incomingCount = values.stream().mapToLong(HistoryListDto::getIncomingCount).sum();
//                    long outgoingCount = values.stream().mapToLong(HistoryListDto::getOutgoingCount).sum();
//                    long adjustCount = values.stream().mapToLong(HistoryListDto::getAdjustCount).sum();
//                    return new HistoryListDto(date, incomingCount, outgoingCount, adjustCount);
//                })
//                .sorted(Comparator.comparing(HistoryListDto::getDate))
//                .collect(Collectors.toList());

        return new ArrayList<>();
    }
}
