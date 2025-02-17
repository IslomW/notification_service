package com.sharipov.notification_service.service.impl;

import com.sharipov.notification_service.service.TelegramService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;


@Service
public class TelegramServiceImpl extends TelegramLongPollingBot implements TelegramService {


    private final String botToken;
    private final String botUsername;

    public TelegramServiceImpl(@Value("${telegram.bot.token}") String botToken,
                               @Value("${telegram.bot.username}") String botUsername) {
        this.botToken = botToken;
        this.botUsername = botUsername;
    }

    @Override
    public String getBotUsername() {
        return botUsername;
    }

    @Override
    public String getBotToken() {
        return botToken;
    }


    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()){
            String chatId = update.getMessage().getChatId().toString();
            String receivedText = update.getMessage().getText();

            System.out.println("Chat ID пользователя: " + chatId);

            if (receivedText.equalsIgnoreCase("/start")){
                sendMessage(chatId, "Hello! Im notification bot");
            }else {
                sendMessage(chatId, "Vi napisali: " + receivedText);
            }
        }
    }


    @Override
    public void sendMessage(String chatId, String text) {
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText(text);

        try{
            execute(message);
        }catch (TelegramApiException e){
            e.printStackTrace();
        }


    }





}
