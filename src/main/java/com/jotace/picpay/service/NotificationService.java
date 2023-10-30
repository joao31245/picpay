package com.jotace.picpay.service;

import com.jotace.picpay.adapters.EmailSenderGateway;
import com.jotace.picpay.core.EmailSenderUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NotificationService implements EmailSenderUseCase {
    private final EmailSenderGateway emailSenderGateway;

    @Autowired
    public NotificationService(EmailSenderGateway emailSenderGateway) {
        this.emailSenderGateway = emailSenderGateway;
    }

    @Override
    public void sendEmail(String to, String subject, String body) {
        this.emailSenderGateway.sendEmail(to,subject,body);
    }
}
