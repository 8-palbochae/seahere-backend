package com.seahere.backend.concurrency;

import com.seahere.backend.outgoing.service.OutgoingService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class OutgoingConcurrencyTest {
    @Autowired
    private OutgoingService outgoingService;

    @Test
    @DisplayName("출고할때 동시성 실패 테스트")
    void outgoging() {
        // given

        // when

        // then
    }
}
