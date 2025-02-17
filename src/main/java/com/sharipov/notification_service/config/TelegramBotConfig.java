package com.sharipov.notification_service.config;

import com.sharipov.notification_service.service.impl.TelegramServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

@Configuration
public class TelegramBotConfig {



    @Bean
    public TelegramBotsApi telegramBotsApi (TelegramServiceImpl telegramService) throws Exception {
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
        telegramBotsApi.registerBot(telegramService);
        return telegramBotsApi;
    }



}
