package com.seahere.backend.user.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.seahere.backend.user.domain.QUserEntity;
import com.seahere.backend.user.domain.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepositoryCustom{
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Optional<UserEntity> findWithCompanyByEmail(String email) {
        UserEntity user = jpaQueryFactory.selectFrom(QUserEntity.userEntity)
                .leftJoin(QUserEntity.userEntity.company).fetchJoin()
                .where(QUserEntity.userEntity.email.eq(email))
                .fetchOne();

        return Optional.ofNullable(user);
    }
}
