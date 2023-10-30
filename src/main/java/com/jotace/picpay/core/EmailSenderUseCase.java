package com.jotace.picpay.core;

public interface EmailSenderUseCase {
    void sendEmail(String to, String subject, String body);
}
