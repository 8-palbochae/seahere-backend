package com.seahere.backend.company.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.seahere.backend.company.controller.response.CompanyResponse;
import com.seahere.backend.company.entity.CompanyEntity;
import com.seahere.backend.company.entity.QCompanyEntity;
import com.seahere.backend.company.controller.request.CompanySearch;
import com.seahere.backend.inventory.entity.QInventoryEntity;
import com.seahere.backend.outgoing.entity.QOutgoingEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class CompanySelectRepository {
    private final JPAQueryFactory jpaQueryFactory;

    public List<CompanyEntity> getList(CompanySearch companySearch){
        return jpaQueryFactory.selectFrom(QCompanyEntity.companyEntity)
                .where(
                        QCompanyEntity.companyEntity.companyName.containsIgnoreCase(companySearch.getSearchWord())
                )
                .limit(companySearch.getSize())
                .offset(companySearch.getOffset())
                .orderBy(QCompanyEntity.companyEntity.id.desc())
                .fetch();
    }

    public List<CompanyEntity> findTradeCompanyList(CompanySearch companySearch, Long companyId) {
        QCompanyEntity company = QCompanyEntity.companyEntity;
        QInventoryEntity inventory = QInventoryEntity.inventoryEntity;

        return jpaQueryFactory.selectDistinct(company) // 중복된 회사 제거
                .from(company)
                .join(inventory).on(company.id.eq(inventory.company.id)) // Company와 Inventory 테이블 조인
                .where(
                        company.id.ne(companyId), // 본인 회사 제외
                        inventory.quantity.gt(0), // 재고가 있는 회사만 필터링
                        company.companyName.containsIgnoreCase(companySearch.getSearchWord()) // 검색어 필터링 (옵션)
                )
                .limit(companySearch.getSize())
                .offset(companySearch.getOffset())
                .orderBy(company.id.desc())
                .fetch();
    }



    public CompanyEntity  findCompanyWithBestOutgoing() {
        QOutgoingEntity outgoing = QOutgoingEntity.outgoingEntity;
        return jpaQueryFactory
                .select(outgoing.company)
                .from(outgoing)
                .groupBy(outgoing.company)
                .orderBy(outgoing.count().desc())
                .limit(1)
                .fetchOne();
    }
}
