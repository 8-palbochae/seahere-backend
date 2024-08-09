package com.seahere.backend.alarm.service;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.seahere.backend.alarm.dto.AlarmToCustomerEvent;
import com.seahere.backend.alarm.entity.AlarmHistoryEntity;
import com.seahere.backend.alarm.entity.AlarmTokenEntity;
import com.seahere.backend.alarm.repository.AlarmHistoryJpaRepository;
import com.seahere.backend.alarm.repository.AlarmJapRepository;
import com.seahere.backend.user.domain.UserEntity;
import com.seahere.backend.user.exception.UserNotFound;
import com.seahere.backend.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionalEventListener;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class AlarmServiceImpl implements AlarmService{

    private final FirebaseMessaging firebaseMessaging;
    private final AlarmHistoryJpaRepository alarmHistoryJpaRepository;
    private final AlarmJapRepository alarmJapRepository;
    private final UserRepository userRepository;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @TransactionalEventListener
    public void createAlarmHistory(AlarmToCustomerEvent event) throws FirebaseMessagingException {
        log.info("이벤트 구독확인 ");
        String token = "e1PgMuaRf2r_3G1OTnRALj:APA91bGcjxQqL4Lj_7ZlynD6L23Y-DsV02kJTdiC5Lu-Z7DuyoWIUocXfC994XC0A2PNBfNSAgySwHVpVBn8hn1zjP_X8cdkXteXJ6hihix0JEk__hXjvkAHNiK6cKW6Hs9A1xOsbrPM";
        sendMessage(token,event.getTitle(),event.getBody());
        alarmHistoryJpaRepository.save(AlarmHistoryEntity.builder()
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

    @Override
    public void saveToken(Long userId, String token){
        UserEntity user = userRepository.findById(userId).orElseThrow(UserNotFound::new);
        Optional<AlarmTokenEntity> alarmTokenOptional = alarmJapRepository.findByUser(user);
        if(alarmTokenOptional.isPresent()){
            AlarmTokenEntity alarmToken = alarmTokenOptional.get();
            alarmToken.tokenUpdate(token);
            return;
        }
        alarmJapRepository.save(AlarmTokenEntity.builder()
                        .user(user)
                        .token(token)
                .build());
    }
}
