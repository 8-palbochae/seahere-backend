package com.seahere.backend.sales.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.NumberTemplate;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.seahere.backend.incoming.entity.QIncomingEntity;
import com.seahere.backend.outgoing.entity.OutgoingState;
import com.seahere.backend.outgoing.entity.QOutgoingDetailEntity;
import com.seahere.backend.outgoing.entity.QOutgoingEntity;
import com.seahere.backend.sales.dto.SalesMonthDto;
import com.seahere.backend.sales.dto.SalesWeekDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class SalesRepositoryImpl implements SalesRepository {

    private final JPAQueryFactory queryFactory;
    QIncomingEntity incoming = QIncomingEntity.incomingEntity;
    QOutgoingEntity outgoing = QOutgoingEntity.outgoingEntity;
    QOutgoingDetailEntity outgoingDetail = QOutgoingDetailEntity.outgoingDetailEntity;

    public List<SalesWeekDto> incomingWeekList(Long companyId, LocalDate startDate, LocalDate endDate) {

        NumberTemplate<Integer> weekNumberTemplate = Expressions.numberTemplate(
                Integer.class, "EXTRACT(WEEK FROM {0})", incoming.incomingDate
        );

        return queryFactory
                .select(Projections.constructor(SalesWeekDto.class,
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


    public List<SalesMonthDto> incomingMonthList(Long companyId, LocalDate startDate, LocalDate endDate) {
        return queryFactory
                .select(Projections.constructor(SalesMonthDto.class,
                        incoming.incomingDate.month(),
                        incoming.incomingPrice.sum()))
                .from(incoming)
                .where(incoming.company.id.eq(companyId)
                        .and(incoming.incomingDate.between(startDate, endDate)))
                .groupBy(incoming.incomingDate.month())
                .fetch();
    }

    @Override
    public List<SalesWeekDto> outgoingWeekList(Long companyId, LocalDate startDate, LocalDate endDate) {
        NumberTemplate<Integer> weekNumberTemplate = Expressions.numberTemplate(
                Integer.class, "EXTRACT(WEEK FROM {0})", outgoing.outgoingDate
        );

        return queryFactory
                .select(Projections.constructor(SalesWeekDto.class,
                        outgoing.outgoingDate.stringValue(), // String으로 변환
                        weekNumberTemplate,
                        outgoingDetail.price.sum().intValue() // Integer로 변환
                ))
                .from(outgoingDetail)
                .join(outgoingDetail.outgoing, outgoing)
                .where(outgoing.company.id.eq(companyId)
                        .and(outgoing.outgoingDate.between(startDate, endDate))
                        .and(outgoing.outgoingState.eq(OutgoingState.COMPLETE))
                )
                .groupBy(outgoing.outgoingDate, weekNumberTemplate)
                .orderBy(outgoing.outgoingDate.asc(), weekNumberTemplate.asc())
                .fetch();
    }

}
