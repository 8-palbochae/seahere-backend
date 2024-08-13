package com.seahere.backend.follow.repository;

import com.seahere.backend.follow.entity.FollowEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FollowJpaRepository extends JpaRepository<FollowEntity, Long>{
    public void deleteByFollowIdAndUserId(Long followId, Long userId);
}
