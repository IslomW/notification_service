package com.sharipov.notification_service.handler;

import com.sharipov.notification_service.event.NotificationEvent;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@KafkaListener(topics = "notification-event-topic")
public class NotificationEventHandler {


    @KafkaHandler
    public void handle(NotificationEvent notificationEvent) {

    }
}
