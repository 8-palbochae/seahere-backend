package com.seahere.backend.sales.repository;

import com.querydsl.core.types.Projections;
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

@Repository
@RequiredArgsConstructor
public class SalesRepositoryImpl implements SalesRepository {

    private final JPAQueryFactory queryFactory;

    public List<IncomingSalesDto> incomingWeekList(Long companyId, LocalDate startDate, LocalDate endDate) {
        QIncomingEntity incoming = QIncomingEntity.incomingEntity;

        // H2에 맞는 SQL 함수 사용
//        StringTemplate formattedDateTemplate = Expressions.stringTemplate(
//                "DATE_FORMAT({0}, 'yyyy-MM-dd')", incoming.incomingDate
//        );

        NumberTemplate<Integer> weekNumberTemplate = Expressions.numberTemplate(
                Integer.class, "EXTRACT(WEEK FROM {0})", incoming.incomingDate
        );

        return queryFactory
                .select(Projections.constructor(IncomingSalesDto.class,
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
}
