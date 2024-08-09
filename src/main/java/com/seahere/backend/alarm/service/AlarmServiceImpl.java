package com.seahere.backend.alarm.service;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.seahere.backend.alarm.dto.AlarmToCustomerEvent;
import com.seahere.backend.alarm.entity.AlarmHistoryEntity;
import com.seahere.backend.alarm.repository.AlarmJpaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionalEventListener;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class AlarmServiceImpl implements AlarmService{

    private final FirebaseMessaging firebaseMessaging;
    private final AlarmJpaRepository alarmJpaRepository;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @TransactionalEventListener
    public void createAlarmHistory(AlarmToCustomerEvent event) throws FirebaseMessagingException {
        log.info("이벤트 구독확인 ");
        String token = "cxa6C8LQ1qsyVTGeG11t_H:APA91bFIycWR_Y5W0D_aPDsd4ZC27B1owSXlw9K1ipdi3Dh-6-4ks0uLbopxH20tBU3OmV9yqOXBtKxgGgW5TLOrDNaHtV7XISzcZAqTC7WV99bP3ygU8EVFkk7H6cNiRNdR1wWPfcgo";
        sendMessage(token,event.getTitle(),event.getBody());
        alarmJpaRepository.save(AlarmHistoryEntity.builder()
                        .userId(event.getUserId())
                        .title(event.getTitle())
                        .body(event.getBody())
                .build());
    }

    @Override
    public void sendMessage(String token, String title, String message) throws FirebaseMessagingException {
        firebaseMessaging.send(FcmMessage.makeMessage(token, title, message));
    }

    @Override
    public void sendMessages(List<String> tokens, String title, String message) throws Exception {
        firebaseMessaging.sendMulticast(FcmMessage.makeMessages(tokens, title, message));
    }
}
