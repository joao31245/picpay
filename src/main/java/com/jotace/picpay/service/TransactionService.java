package com.jotace.picpay.service;

import com.jotace.picpay.domain.transaction.Transaction;
import com.jotace.picpay.dto.TransactionRequest;
import com.jotace.picpay.repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class TransactionService {
    @Autowired
    private UserService userService;

    @Autowired
    private TransactionRepository repository;
    private final NotificationService notificationService;

    @Autowired
    public TransactionService(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    public Transaction createTransaction(TransactionRequest request) {
        var sender = userService.findUserById(request.senderId());
        var receiver = userService.findUserById(request.receiverId());
        var value = request.value();

        userService.validate(sender, value);

        sender.setAmount(sender.getAmount().subtract(value));
        receiver.setAmount(receiver.getAmount().add(value));

        var transaction = new Transaction();

        transaction.setSender(sender);
        transaction.setReceiver(receiver);
        transaction.setTransactionValue(value);
        transaction.setDate(LocalDate.now());

        notificationService.sendEmail(sender.getEmail(),"Money transaction", "You sent "
                + transaction.getTransactionValue() + " to " + receiver.getFullName());

        notificationService.sendEmail(receiver.getEmail(),"Money transaction", "You received "
                + transaction.getTransactionValue() + " from " + sender.getFullName());

        userService.save(sender);
        userService.save(receiver);
        repository.save(transaction);

        return transaction;
    }
}
