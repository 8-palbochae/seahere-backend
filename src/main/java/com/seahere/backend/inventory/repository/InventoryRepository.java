package com.seahere.backend.inventory.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.seahere.backend.inventory.controller.response.InventoryReqDto;
import com.seahere.backend.inventory.entity.InventoryEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import java.util.Iterator;
import java.util.List;
import java.util.function.Function;


@Repository
@RequiredArgsConstructor
public class InventoryRepository {
    private final JPAQueryFactory queryFactory;
    public Slice<InventoryReqDto> findAllInventoryBySearch(Long companyId, Pageable pageable, String search) {
        return new Slice<InventoryReqDto>() {
            @Override
            public int getNumber() {
                return 0;
            }

            @Override
            public int getSize() {
                return 0;
            }

            @Override
            public int getNumberOfElements() {
                return 0;
            }

            @Override
            public List<InventoryReqDto> getContent() {
                return List.of();
            }

            @Override
            public boolean hasContent() {
                return false;
            }

            @Override
            public Sort getSort() {
                return null;
            }

            @Override
            public boolean isFirst() {
                return false;
            }

            @Override
            public boolean isLast() {
                return false;
            }

            @Override
            public boolean hasNext() {
                return false;
            }

            @Override
            public boolean hasPrevious() {
                return false;
            }

            @Override
            public Pageable nextPageable() {
                return null;
            }

            @Override
            public Pageable previousPageable() {
                return null;
            }

            @Override
            public <U> Slice<U> map(Function<? super InventoryReqDto, ? extends U> converter) {
                return null;
            }

            @Override
            public Iterator<InventoryReqDto> iterator() {
                return null;
            }
        };
    }
}