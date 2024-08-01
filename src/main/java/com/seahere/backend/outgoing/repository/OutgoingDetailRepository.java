package com.seahere.backend.outgoing.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class OutgoingDetailRepository {

    private final JPAQueryFactory queryFactory;


}
