package com.jotace.picpay.dto.user;

import com.jotace.picpay.domain.user.UserType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record UserRequest(
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
}
