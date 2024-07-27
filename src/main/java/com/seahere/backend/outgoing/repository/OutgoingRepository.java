package com.seahere.backend.outgoing.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.seahere.backend.outgoing.entity.OutgoingEntity;
import com.seahere.backend.outgoing.entity.OutgoingState;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.seahere.backend.outgoing.entity.QOutgoingDetailEntity.outgoingDetailEntity;
import static com.seahere.backend.outgoing.entity.QOutgoingEntity.outgoingEntity;

@Repository
@RequiredArgsConstructor
public class OutgoingRepository {

    private final JPAQueryFactory queryFactory;

    public List<OutgoingEntity> findByOutgoingStateIsPending(Long companyId){
        return queryFactory.selectFrom(outgoingEntity)
                .leftJoin(outgoingEntity.outgoingDetails, outgoingDetailEntity)
                .fetchJoin()
                .where(outgoingEntity.outgoingState.eq(OutgoingState.pending),outgoingEntity.companyId.eq(companyId))
                .fetch();
    }
}
