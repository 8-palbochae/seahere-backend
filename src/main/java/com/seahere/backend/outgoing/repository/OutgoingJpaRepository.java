package com.seahere.backend.outgoing.repository;

import com.seahere.backend.outgoing.entity.OutgoingEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OutgoingJpaRepository extends JpaRepository<OutgoingEntity, Long> {

}
