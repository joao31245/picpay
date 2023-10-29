package com.jotace.picpay.service;

import com.jotace.picpay.domain.user.User;
import com.jotace.picpay.domain.user.UserType;
import com.jotace.picpay.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class UserService {
    @Autowired
    private UserRepository repository;

    public void validate(User sender) {
        if(sender.getUserType().equals(UserType.MERCHANT)) {
            throw new RuntimeException("Merchants can't make any transaction");
        }

    }
}
