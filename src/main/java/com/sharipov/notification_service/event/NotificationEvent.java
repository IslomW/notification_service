package com.sharipov.notification_service.event;

import java.time.LocalDateTime;

public class NotificationEvent {
        private String recipient;
        private String message;
        private NotificationChannel channel;
        private LocalDateTime timestamp;


        public NotificationEvent() {
        }

        public NotificationEvent(String recipient, String message, NotificationChannel channel, LocalDateTime timestamp) {
                this.recipient = recipient;
                this.message = message;
                this.channel = channel;
                this.timestamp = timestamp;
        }

        public String getRecipient() {
                return recipient;
        }

        public void setRecipient(String recipient) {
                this.recipient = recipient;
        }

        public String getMessage() {
                return message;
        }

        public void setMessage(String message) {
                this.message = message;
        }

        public NotificationChannel getChannel() {
                return channel;
        }

        public void setChannel(NotificationChannel channel) {
                this.channel = channel;
        }

        public LocalDateTime getTimestamp() {
                return timestamp;
        }

        public void setTimestamp(LocalDateTime timestamp) {
                this.timestamp = timestamp;
        }
}
