package com.jotace.picpay.dto;

import com.jotace.picpay.domain.user.User;
import com.jotace.picpay.domain.user.UserType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record UserResponse(
        @NotNull
        Long id,
        @NotNull
        String fullName,
        @NotNull
        String cpf,
        @Email
        @NotNull
        String email,
        @NotNull
        String password,
        UserType userType,
        @NotNull
        BigDecimal amount
) {
    public UserResponse(User user) {
        this(user.getId(), user.getFullName(), user.getCpf(), user.getEmail(),
                user.getPassword(), user.getUserType(), user.getAmount());
    }
}
