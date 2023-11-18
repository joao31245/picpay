package com.jotace.picpay.service;

import com.jotace.picpay.domain.transaction.State;
import com.jotace.picpay.domain.transaction.Transaction;
import com.jotace.picpay.domain.transaction.TransactionType;
import com.jotace.picpay.domain.user.User;
import com.jotace.picpay.dto.TransactionRequest;
import com.jotace.picpay.dto.TransactionResponse;
import com.jotace.picpay.repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Service
public class TransactionService {
    @Autowired
    private UserService userService;
    @Autowired
    private TransactionRepository repository;
    private final NotificationService notificationService;
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);


    @Autowired
    public TransactionService(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    public TransactionResponse createTransaction(TransactionRequest request) throws InterruptedException {
        var sender = userService.findUserById(request.senderId());
        var receiver = userService.findUserById(request.receiverId());
        var value = request.value();

        if(!sender.isActive() || !receiver.isActive()) {
            throw new RuntimeException("One or more of the users are not active anymore");
        }

        userService.validate(sender, value);

        var transaction = new Transaction();

        transaction.setSender(sender);
        transaction.setReceiver(receiver);
        transaction.setTransactionValue(value);
        transaction.setTransactionType(request.transactionType());

        if(transaction.getTransactionType().equals(TransactionType.SCHEDULED)) {
            transaction.setDate(request.localDateTime());

            var currentTime = LocalDateTime.now();

            var scheduledTime = transaction.getDate();

            var delay = Duration.between(currentTime, scheduledTime).toMillis();

            if(delay < 0) {
                throw new RuntimeException("Just pass date in the future.");
            }

            transaction.setState(State.PENDENT);
            repository.save(transaction);

            scheduler.schedule(() -> processTransaction(sender, receiver, transaction), delay, TimeUnit.MILLISECONDS);

            return new TransactionResponse(transaction);
        } else {

            transaction.setDate(LocalDateTime.now());

            processTransaction(sender, receiver, transaction);

            return new TransactionResponse(transaction);
        }

    }

   public void processTransaction(User sender, User receiver, Transaction transaction) {
       var value = transaction.getTransactionValue();
       sender.setAmount(sender.getAmount().subtract(value));
       receiver.setAmount(receiver.getAmount().add(value));

       transaction.setState(State.PAID);

       userService.save(sender);
       userService.save(receiver);
       repository.save(transaction);

       sendNotification(sender, receiver, transaction);
    }

    public void sendNotification(User sender, User receiver, Transaction transaction) {
        notificationService.sendEmail(sender.getEmail(),"Money transaction", "You sent "
                + transaction.getTransactionValue() + " to " + receiver.getFullName());

        notificationService.sendEmail(receiver.getEmail(),"Money transaction", "You received "
                + transaction.getTransactionValue() + " from " + sender.getFullName());
    }
}
