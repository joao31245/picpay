package com.jotace.picpay.dto;

import com.jotace.picpay.domain.transaction.State;
import com.jotace.picpay.domain.transaction.Transaction;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public record TransactionResponse(

        @NotNull
        Long id,
        @NotNull
        Long receiverId,
        @NotNull
        Long senderId,
        @NotNull
        BigDecimal value,

        LocalDateTime date,

        State state
) {
        public TransactionResponse(Transaction transaction) {
                this(transaction.getId(), transaction.getReceiver().getId(),
                        transaction.getSender().getId(), transaction.getTransactionValue(),
                        transaction.getDate(), transaction.getState());
        }
}
