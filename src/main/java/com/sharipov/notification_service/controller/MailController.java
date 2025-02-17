package com.sharipov.notification_service.controller;

import com.sharipov.notification_service.config.MailType;
import com.sharipov.notification_service.entity.Profile;

import com.sharipov.notification_service.service.MailService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Properties;

@RestController
@RequestMapping("api/v1/send")

public class MailController {

    private final MailService mailService;


    public MailController(MailService mailService) {
        this.mailService = mailService;
    }

    @PostMapping
    public ResponseEntity<Void> sendEmailMessage(@RequestBody Profile profile){
        mailService.sendEmail(profile, MailType.REGISTRATION, new Properties());
        return ResponseEntity.ok().build();
    }

}
