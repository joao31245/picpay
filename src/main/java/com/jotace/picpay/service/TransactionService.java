package com.jotace.picpay.service;

import com.jotace.picpay.domain.transaction.Transaction;
import com.jotace.picpay.dto.TransactionRequest;
import com.jotace.picpay.dto.TransactionResponse;
import com.jotace.picpay.repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class TransactionService {
    @Autowired
    private UserService service;

    @Autowired
    private TransactionRepository repository;

    public TransactionResponse createTransaction(TransactionRequest request) {
        var sender = service.findUserById(request.senderId());
        var receiver = service.findUserById(request.receiverId());
        var value = request.value();

        service.validate(sender, value);

        sender.setAmount(sender.getAmount().subtract(value));
        receiver.setAmount(sender.getAmount().add(value));

        var transaction = new Transaction();

        transaction.setSender(sender);
        transaction.setReceiver(receiver);
        transaction.setTransactionValue(value);
        transaction.setDate(LocalDate.now());

        service.save(sender);
        service.save(receiver);
        repository.save(transaction);

        return new TransactionResponse(transaction);
    }
}
