package com.sharipov.notification_service.controller;

import com.sharipov.notification_service.service.impl.TelegramServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/tg")
public class TelegramController {

    private final Logger log = LoggerFactory.getLogger(TelegramController.class);
    private final TelegramServiceImpl telegramService;

    public TelegramController(TelegramServiceImpl telegramService) {
        this.telegramService = telegramService;
    }

    @PostMapping
    public String sendMessage (@RequestBody Telegram messageInfo){
        log.info(messageInfo.text());
        telegramService.sendMessage(messageInfo.chatId(), messageInfo.text());
        return "Success";
    }
}
