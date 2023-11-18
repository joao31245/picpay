package com.jotace.picpay.dto.user;

public record UpdateRequest(
        Long id,
        String email,
        String password
) {
}
