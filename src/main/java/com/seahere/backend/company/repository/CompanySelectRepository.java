package com.seahere.backend.company.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.seahere.backend.company.controller.response.CompanyResponse;
import com.seahere.backend.company.entity.CompanyEntity;
import com.seahere.backend.company.entity.QCompanyEntity;
import com.seahere.backend.company.controller.request.CompanySearch;
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
