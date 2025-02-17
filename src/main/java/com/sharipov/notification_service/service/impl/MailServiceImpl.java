package com.sharipov.notification_service.service.impl;

import com.sharipov.notification_service.config.MailType;
import com.sharipov.notification_service.entity.Profile;
import com.sharipov.notification_service.service.MailService;
import freemarker.template.Configuration;
import freemarker.template.TemplateException;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;


@Service
public class MailServiceImpl implements MailService {
    private static final Logger logger = LoggerFactory.getLogger(MailServiceImpl.class);
    private final JavaMailSender javaMailSender;
    private final Configuration configuration;


    public MailServiceImpl(JavaMailSender javaMailSender, Configuration configuration) {
        this.javaMailSender = javaMailSender;
        this.configuration = configuration;
    }

    @Override
    public void sendEmail(Profile profile, MailType mailType, Properties properties) {

        try {
            switch (mailType) {
                case REGISTRATION -> sendRegistrationEmail(profile, properties);
                default -> {
                }
            }
        } catch (Exception e) {
            logger.error("Failed to send email to {}: {}", profile.getEmail(), e.getMessage(), e);
        }


    }


    private void sendRegistrationEmail(Profile profile, Properties params) {
        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, false, "UTF-8");
            helper.setSubject("Thank you for registration " + profile.getName());
            helper.setTo(profile.getEmail());
            String emailContent = getRegistrationContent(profile, params);
            helper.setText(emailContent, true);
            javaMailSender.send(message);

        } catch (MessagingException e) {
            logger.error("Failed to create or send email message: {}", e.getMessage(), e);
        }

    }


    private String getRegistrationContent(Profile profile, Properties params) {
        try {
            StringWriter writer = new StringWriter();
            Map<String, Object> model = new HashMap<>();
            model.put("name", profile.getName());
            configuration.getTemplate("register.ftlh")
                    .process(model, writer);
            return writer.getBuffer().toString();
        } catch (IOException | TemplateException e) {
            logger.error("Failed to generate email content: {}", e.getMessage(), e);
            return "Error generating email content.";
        }
    }
}
