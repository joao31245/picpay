package com.jotace.picpay.infra.exceptions;

import com.jotace.picpay.dto.ExceptionResponse;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerAdvice {
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ExceptionResponse>
    threatDuplicate(DataIntegrityViolationException exception) {
        var response = new ExceptionResponse("User already registered", "400");
        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<String> threatEntityNotFound(EntityNotFoundException exception) {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ExceptionResponse> threatGeneral(RuntimeException exception) {
        var response = new ExceptionResponse(exception.getMessage(), "500");
        return ResponseEntity.internalServerError().body(response);
    }
}
