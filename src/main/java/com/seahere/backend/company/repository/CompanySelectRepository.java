package com.seahere.backend.company.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.seahere.backend.company.entity.CompanyEntity;
import com.seahere.backend.company.entity.QCompanyEntity;
import com.seahere.backend.company.request.CompanySearch;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class CompanySelectRepository {
    private final JPAQueryFactory jpaQueryFactory;

    public List<CompanyEntity> getList(CompanySearch companySearch){
        return jpaQueryFactory.selectFrom(QCompanyEntity.companyEntity)
                .limit(companySearch.getSize())
                .offset(companySearch.getOffset())
                .orderBy(QCompanyEntity.companyEntity.id.desc())
                .fetch();
    }
}
