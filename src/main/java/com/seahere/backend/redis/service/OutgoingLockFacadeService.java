package com.seahere.backend.redis.service;

import com.seahere.backend.outgoing.entity.OutgoingEntity;
import com.seahere.backend.outgoing.service.OutgoingService;
import com.seahere.backend.redis.respository.RedisLockRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OutgoingLockFacadeService {

    private final static String TYPE = "outgoing";

    private final RedisLockRepository redisLockRepository;
    private final OutgoingService outgoingService;

    public OutgoingEntity acceptOutgoingCall(Long outgoingId) throws InterruptedException {
        while(!redisLockRepository.lock(outgoingId,TYPE)){
            Thread.sleep(100);
        }
        try{
            return outgoingService.acceptOutgoingCall(outgoingId);
        }finally {
            redisLockRepository.unLock(outgoingId,TYPE);
        }
    }
}
