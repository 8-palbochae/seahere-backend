package com.seahere.backend.incoming.repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.seahere.backend.incoming.entity.IncomingEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

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

    public List<IncomingEntity> findIncomingList(Long companyId, LocalDate startDate, LocalDate endDate){
        List<IncomingEntity> results = queryFactory.selectFrom(incomingEntity)
                .leftJoin(incomingEntity.company,companyEntity).fetchJoin()
//                .leftJoin(incomingEntity.user, userEntity).fetchJoin()
                .leftJoin(incomingEntity.product,productEntity).fetchJoin()
                .where(incomingPeriodFindList(companyId,startDate,endDate))
                .fetch();

        return new ArrayList<>(results);
    }

    private BooleanExpression incomingPeriodFindList(Long companyId,LocalDate startDate, LocalDate endDate){
        return incomingEntity.company.id.eq(companyId)
                .and(incomingEntity.incomingDate.goe(startDate))
                .and(incomingEntity.incomingDate.loe(endDate));
    }


}
