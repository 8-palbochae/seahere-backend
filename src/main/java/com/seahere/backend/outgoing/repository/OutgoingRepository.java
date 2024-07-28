package com.seahere.backend.outgoing.repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.seahere.backend.outgoing.entity.OutgoingEntity;
import com.seahere.backend.outgoing.entity.OutgoingState;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

import static com.seahere.backend.outgoing.entity.QOutgoingDetailEntity.outgoingDetailEntity;
import static com.seahere.backend.outgoing.entity.QOutgoingEntity.outgoingEntity;

@Repository
@RequiredArgsConstructor
public class OutgoingRepository {

    private final JPAQueryFactory queryFactory;

    public Slice<OutgoingEntity> findByOutgoingStateIsPending(Long companyId, Pageable pageable, LocalDate startDate, LocalDate endDate, String search) {
        List<OutgoingEntity> results = queryFactory.selectFrom(outgoingEntity).distinct()
                .leftJoin(outgoingEntity.outgoingDetails, outgoingDetailEntity)
                .where(outgoingStateIsPending(companyId,startDate,endDate,search))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize() + 1)
                .fetch();
        boolean hasNext = results.size() > pageable.getPageSize();
        if(hasNext){
            results.remove(results.size() - 1);
        }
        return new SliceImpl<>(results,pageable, hasNext);
    }
    private BooleanExpression outgoingStateIsPending(Long companyId,LocalDate startDate, LocalDate endDate, String search) {
        return outgoingEntity.outgoingState.eq(OutgoingState.pending)
                .and(outgoingEntity.companyId.eq(companyId))
                .and(outgoingEntity.outgoingDate.goe(startDate))
                .and(outgoingEntity.outgoingDate.loe(endDate))
                .and(outgoingEntity.customerName.contains(search).or(outgoingDetailEntity.productName.contains(search)));
    }

}
