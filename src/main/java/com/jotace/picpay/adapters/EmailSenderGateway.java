package com.jotace.picpay.adapters;

public interface EmailSenderGateway {
    void sendEmail(String to, String subject, String body);
}
