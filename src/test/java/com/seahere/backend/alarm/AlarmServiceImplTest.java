package com.seahere.backend.alarm;

import com.seahere.backend.alarm.service.AlarmService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class AlarmServiceImplTest {

    @Autowired
    private AlarmService alarmService;

    @Test
    @DisplayName("알람발송테스트")
    void sendMessage() throws Exception {
        // given
        String token = "cxa6C8LQ1qsyVTGeG11t_H:APA91bFIycWR_Y5W0D_aPDsd4ZC27B1owSXlw9K1ipdi3Dh-6-4ks0uLbopxH20tBU3OmV9yqOXBtKxgGgW5TLOrDNaHtV7XISzcZAqTC7WV99bP3ygU8EVFkk7H6cNiRNdR1wWPfcgo";
        String token1 = "e1PgMuaRf2r_3G1OTnRALj:APA91bGcjxQqL4Lj_7ZlynD6L23Y-DsV02kJTdiC5Lu-Z7DuyoWUocXfC994XC0A2PNBfNSAgySwHVpVBn8hn1zjP_X8cdkXteXJ6hihix0JEk__hXjvkAHNiK6cKW6Hs9A1xOsbrPM";
        // when
        // then
        alarmService.sendMessage(token,"껄껄","내용입니당.");
    }


}