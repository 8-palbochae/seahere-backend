package com.seahere.backend.sales.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.NumberTemplate;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.seahere.backend.incoming.entity.QIncomingEntity;
import com.seahere.backend.sales.dto.IncomingMonthDto;
import com.seahere.backend.sales.dto.IncomingWeekDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class SalesRepositoryImpl implements SalesRepository {

    private final JPAQueryFactory queryFactory;
    QIncomingEntity incoming = QIncomingEntity.incomingEntity;

    public List<IncomingWeekDto> incomingWeekList(Long companyId, LocalDate startDate, LocalDate endDate) {

        NumberTemplate<Integer> weekNumberTemplate = Expressions.numberTemplate(
                Integer.class, "EXTRACT(WEEK FROM {0})", incoming.incomingDate
        );

        return queryFactory
                .select(Projections.constructor(IncomingWeekDto.class,
                        incoming.incomingDate.as("incomingDate"),
                        weekNumberTemplate.as("week"),
                        incoming.incomingPrice.sum().as("incomingPrice")
                ))
                .from(incoming)
                .where(incoming.company.id.eq(companyId)
                        .and(incoming.incomingDate.between(startDate, endDate)))
                .groupBy(incoming.incomingDate, weekNumberTemplate)
                .orderBy(incoming.incomingDate.asc(), weekNumberTemplate.asc())
                .fetch();
    }


    public List<IncomingMonthDto> incomingMonthList(Long companyId, LocalDate startDate, LocalDate endDate) {
        return queryFactory
                .select(Projections.constructor(IncomingMonthDto.class,
                        incoming.incomingDate.month(),
                        incoming.incomingPrice.sum()))
                .from(incoming)
                .where(incoming.company.id.eq(companyId)
                        .and(incoming.incomingDate.between(startDate, endDate)))
                .groupBy(incoming.incomingDate.month())
                .fetch();
    }
}
