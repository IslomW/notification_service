package com.sharipov.notification_service.service;

import com.sharipov.notification_service.config.MailType;
import com.sharipov.notification_service.entity.Profile;

import java.util.Properties;

public interface MailService {
    void sendEmail(Profile profile, MailType mailType, Properties properties);
}
