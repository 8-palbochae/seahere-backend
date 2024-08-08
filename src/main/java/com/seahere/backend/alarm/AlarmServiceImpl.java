package com.seahere.backend.alarm;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AlarmServiceImpl implements AlarmService{

    private final FirebaseMessaging firebaseMessaging;

    @Override
    public void sendMessage(String token, String title, String message) throws FirebaseMessagingException {
        firebaseMessaging.send(FcmMessage.makeMessage(token, title, message));
    }

    @Override
    public void sendMessages(List<String> tokens, String title, String message) throws Exception {
        firebaseMessaging.sendMulticast(FcmMessage.makeMessages(tokens, title, message));
    }
}
