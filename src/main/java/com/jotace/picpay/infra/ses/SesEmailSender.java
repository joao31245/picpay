package com.jotace.picpay.infra.ses;

import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.amazonaws.services.simpleemail.model.*;
import com.jotace.picpay.adapters.EmailSenderGateway;
import com.jotace.picpay.core.EmailSenderUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SesEmailSender implements EmailSenderGateway {

    private final AmazonSimpleEmailService amazonSimpleEmailService;

    @Autowired
    public SesEmailSender(AmazonSimpleEmailService amazonSimpleEmailService) {
        this.amazonSimpleEmailService = amazonSimpleEmailService;
    }


    @Override
    public void sendEmail(String to, String subject, String body) {
       var request = new SendEmailRequest()
               .withSource("")
               .withDestination(new Destination().withToAddresses(to))
               .withMessage(new Message()
                       .withSubject(new Content(subject))
                       .withBody(new Body().withText(new Content(body))));

    }
}
