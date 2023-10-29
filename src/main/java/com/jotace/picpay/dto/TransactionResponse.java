package com.jotace.picpay.dto;

import com.jotace.picpay.domain.transaction.Transaction;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;

public record TransactionResponse(

        @NotNull
        Long id,
        @NotNull
        Long receiverId,
        @NotNull
        Long senderId,
        @NotNull
        BigDecimal value,

        LocalDate date
) {
        public TransactionResponse(Transaction transaction) {
                this(transaction.getId(), transaction.getReceiver().getId(),
                        transaction.getSender().getId(), transaction.getTransactionValue(),
                        transaction.getDate());
        }
}
