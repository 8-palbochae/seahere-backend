package com.seahere.backend.alarm;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class AlarmServiceImplTest {

    @Autowired
    private AlarmService alarmService;

    @Test
    @DisplayName("알람발송테스트")
    void sendMessage() throws Exception {
        // given
        String token = "cxa6C8LQ1qsyVTGeG11t_H:APA91bFIycWR_Y5W0D_aPDsd4ZC27B1owSXlw9K1ipdi3Dh-6-4ks0uLbopxH20tBU3OmV9yqOXBtKxgGgW5TLOrDNaHtV7XISzcZAqTC7WV99bP3ygU8EVFkk7H6cNiRNdR1wWPfcgo";
        // when
        // then
        alarmService.sendMessage(token,"껄껄","내용입니당.");
    }


}