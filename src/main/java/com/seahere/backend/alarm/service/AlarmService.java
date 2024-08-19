package com.seahere.backend.alarm.service;

import java.util.List;

public interface AlarmService {

    void sendMessage(String token,String title, String message) throws Exception;
    void sendMessage(String token,String title, String message, String url) throws Exception;
    void sendMessages(List<String> tokens, String title, String message) throws Exception;
    void saveToken(Long userId, String token);
    void saveAlarmClickLog(String fishLog, Long userId);
}
