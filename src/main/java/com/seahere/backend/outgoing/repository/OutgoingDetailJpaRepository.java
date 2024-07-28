package com.seahere.backend.outgoing.repository;

import com.seahere.backend.outgoing.entity.OutgoingDetailEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OutgoingDetailJpaRepository extends JpaRepository<OutgoingDetailEntity , Long> {
}
