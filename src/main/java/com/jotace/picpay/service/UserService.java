package com.jotace.picpay.service;

import com.jotace.picpay.domain.user.User;
import com.jotace.picpay.domain.user.UserType;
import com.jotace.picpay.dto.user.DeleteRequest;
import com.jotace.picpay.dto.user.UpdateRequest;
import com.jotace.picpay.dto.user.UserResponse;
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
        return repository.findAllByActiveTrue();
    }
    public User delete(DeleteRequest request) {
        var user = this.findUserById(request.id());

        user.delete();
        user.setEmail(user.getEmail() + ".OLD");
        repository.save(user);
        return user;
    }

    public UserResponse update(UpdateRequest request) {
        var user = this.findUserById(request.id());

        if(request.email().isBlank()|| request.email().equals(user.getEmail())) {
            throw new RuntimeException("Email is null or equal the older");
        }
        if(request.password().isBlank() || request.password().equals(user.getEmail())) {
            throw new RuntimeException("Password is null or equal the older");
        }

        user.update(request.email(), request.password());

        save(user);

        return new UserResponse(user);
    }

}
