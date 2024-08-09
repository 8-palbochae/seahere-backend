package com.seahere.backend.alarm.repository;

import com.seahere.backend.alarm.entity.AlarmHistoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlarmJpaRepository extends JpaRepository<AlarmHistoryEntity, Long> {
}
