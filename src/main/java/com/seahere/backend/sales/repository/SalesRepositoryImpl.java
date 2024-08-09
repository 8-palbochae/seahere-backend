package com.seahere.backend.sales.repository;

import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.NumberTemplate;
import com.querydsl.core.types.dsl.StringTemplate;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.seahere.backend.incoming.entity.QIncomingEntity;
import com.seahere.backend.sales.dto.IncomingSalesDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class SalesRepositoryImpl {

    private final JPAQueryFactory queryFactory;

    public List<IncomingSalesDto> incomingWeekList(Long companyId, LocalDate startDate, LocalDate endDate) {
        QIncomingEntity incoming = QIncomingEntity.incomingEntity;

        // Define the templates for day and week
        StringTemplate dayTemplate = Expressions.stringTemplate(
                "TO_CHAR({0}, 'yyyy-MM-dd')", incoming.incomingDate
        );

        NumberTemplate<Integer> weekTemplate = Expressions.numberTemplate(
                Integer.class, "EXTRACT(ISO_WEEK FROM {0})", incoming.incomingDate
        );

        return queryFactory
                .select(
                        Expressions.as(dayTemplate, "incomingDate"),
                        weekTemplate.as("week"),
                        incoming.incomingPrice.sum().as("incomingPrice")
                )
                .from(incoming)
                .where(incoming.company.id.eq(companyId)
                        .and(incoming.incomingDate.between(startDate, endDate)))
                .groupBy(dayTemplate, weekTemplate)
                .orderBy(dayTemplate.asc(), weekTemplate.asc())
                .fetch()
                .stream()
                .map(tuple -> IncomingSalesDto.builder()
                        .incomingDate(LocalDate.parse(tuple.get(dayTemplate)))
                        .week(tuple.get(weekTemplate))
                        .incomingPrice(tuple.get(incoming.incomingPrice.sum()))
                        .build())
                .collect(Collectors.toList());
    }

}
