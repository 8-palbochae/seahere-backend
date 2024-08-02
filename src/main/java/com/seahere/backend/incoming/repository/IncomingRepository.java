package com.seahere.backend.incoming.repository;

import com.querydsl.core.Tuple;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.seahere.backend.incoming.dto.IncomingReqDto;
import com.seahere.backend.incoming.entity.IncomingEntity;
import lombok.RequiredArgsConstructor;
import org.hibernate.criterion.Projection;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.parser.Entity;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static com.seahere.backend.company.entity.QCompanyEntity.companyEntity;
import static com.seahere.backend.incoming.entity.QIncomingEntity.incomingEntity;
import static com.seahere.backend.product.entity.QProductEntity.productEntity;
import static com.seahere.backend.user.domain.QUserEntity.userEntity;

@Repository
@RequiredArgsConstructor
public class IncomingRepository {

    private final JPAQueryFactory queryFactory;

    public List<IncomingReqDto> findIncomingList(Long companyId, LocalDate startDate, LocalDate endDate){
        return queryFactory.select(Projections.constructor(IncomingReqDto.class, incomingEntity.incomingDate, incomingEntity.incomingId.count()))
                .from(incomingEntity)
                .leftJoin(incomingEntity.company, companyEntity)
                .leftJoin(incomingEntity.product, productEntity)
                .where(incomingPeriodFindList(companyId, startDate, endDate))
                .groupBy(incomingEntity.incomingDate)
                .fetch();


    }

    private BooleanExpression incomingPeriodFindList(Long companyId,LocalDate startDate, LocalDate endDate){
        return incomingEntity.company.id.eq(companyId)
                .and(incomingEntity.incomingDate.goe(startDate))
                .and(incomingEntity.incomingDate.loe(endDate));
    }


}