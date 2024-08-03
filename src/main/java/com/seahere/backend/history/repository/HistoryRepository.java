package com.seahere.backend.history.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.seahere.backend.history.dto.HistoryListDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
@Repository
public class HistoryRepository {

    private final JPAQueryFactory queryFactory;

    public List<HistoryListDto> findByHistoryDate(LocalDate startDate, LocalDate endDate){
        return null;
    }
}
