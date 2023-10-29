package com.jotace.picpay.service;

import com.jotace.picpay.domain.transaction.Transaction;
import com.jotace.picpay.domain.user.User;
import com.jotace.picpay.repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;

public class TransactionService {
    @Autowired
    private UserService service;

    @Autowired
    private TransactionRepository repository;

    public Transaction createTransaction(User sender, BigDecimal value) {

    }
}
