package com.seahere.backend.alarm;

import java.util.List;

public interface AlarmService {

    void sendMessage(String token,String title, String message) throws Exception;
    void sendMessages(List<String> tokens, String title, String message) throws Exception;
}
