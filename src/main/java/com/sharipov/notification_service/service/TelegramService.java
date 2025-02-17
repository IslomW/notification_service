package com.sharipov.notification_service.service;

public interface TelegramService {
    void sendMessage(String chatId, String text);
}
