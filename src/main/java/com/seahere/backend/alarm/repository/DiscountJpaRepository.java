package com.seahere.backend.alarm.repository;

import com.seahere.backend.alarm.entity.DiscountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiscountJpaRepository extends JpaRepository<DiscountEntity, Long> {
}
