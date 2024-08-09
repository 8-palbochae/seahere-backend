package com.seahere.backend.sales.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class SalesRepositoryImpl {

    private final JPAQueryFactory queryFactory;

}
