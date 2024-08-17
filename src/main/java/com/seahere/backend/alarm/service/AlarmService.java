package com.seahere.backend.alarm.service;

import com.seahere.backend.alarm.controller.request.DiscountRequest;

import java.util.List;

public interface AlarmService {

    void sendMessage(String token,String title, String message) throws Exception;
    void sendMessages(List<String> tokens, String title, String message) throws Exception;
    void saveToken(Long userId, String token);
}
