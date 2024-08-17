package com.seahere.backend.follow.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.seahere.backend.follow.controller.response.FollowReqResponse;
import com.seahere.backend.follow.entity.QFollowEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.seahere.backend.company.entity.QCompanyEntity.companyEntity;
import static com.seahere.backend.user.domain.QUserEntity.userEntity;

@Repository
@RequiredArgsConstructor
public class FollowRepository {
    private final JPAQueryFactory queryFactory;
    private static final QFollowEntity follow = QFollowEntity.followEntity;

    public List<FollowReqResponse> findAllFollowingByUserId(Long userId) {
        return queryFactory
                .select(Projections.constructor(FollowReqResponse.class,
                        follow.followId,
                        follow.company.id,
                        follow.company.companyName))
                .from(follow)
                .leftJoin(follow.company, companyEntity)
                .where(follow.user.id.eq(userId))
                .orderBy(follow.company.companyName.asc())
                .fetch();
    }
}
