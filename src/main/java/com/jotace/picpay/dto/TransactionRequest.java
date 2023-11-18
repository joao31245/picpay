package com.jotace.picpay.dto;

import com.jotace.picpay.domain.transaction.TransactionType;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record TransactionRequest(
        @NotNull
        Long receiverId,
        @NotNull
        Long senderId,
        @NotNull
        BigDecimal value,

        LocalDateTime localDateTime,

        TransactionType transactionType
) {
}
