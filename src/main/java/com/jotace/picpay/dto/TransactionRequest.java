package com.jotace.picpay.dto;

import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record TransactionRequest(
        @NotNull
        Long receiverId,
        @NotNull
        Long senderId,
        @NotNull
        BigDecimal value

) {
}
