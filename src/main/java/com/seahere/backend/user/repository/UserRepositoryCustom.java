package com.seahere.backend.user.repository;

import com.seahere.backend.user.domain.UserEntity;

import java.util.Optional;

public interface UserRepositoryCustom {
    Optional<UserEntity> findWithCompanyByEmail(String email);
}
