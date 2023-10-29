package com.jotace.picpay.service;

import com.jotace.picpay.domain.user.User;
import com.jotace.picpay.domain.user.UserType;
import com.jotace.picpay.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository repository;

    public void validate(User sender, BigDecimal value) {
        if(sender.getUserType().equals(UserType.MERCHANT)) {
            throw new RuntimeException("Merchants can't make any transaction");
        }
        if(sender.getAmount().compareTo(value) < 0) {
            throw new RuntimeException("Money is not enough");
        }
    }

    public User findUserById(Long id) {
        return repository.findUserById(id);
    }

    public void save(User user) {
        repository.save(user);
    }

    public List<User> getAllUsers() {
        return repository.findAll();
    }

}
