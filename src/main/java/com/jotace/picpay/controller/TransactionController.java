package com.jotace.picpay.controller;

import com.jotace.picpay.dto.TransactionRequest;
import com.jotace.picpay.dto.TransactionResponse;
import com.jotace.picpay.service.TransactionService;
import com.jotace.picpay.service.UserService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("transaction")
public class TransactionController {
    @Autowired
    private TransactionService transactionService;

    @PostMapping
    @Transactional
    public ResponseEntity<TransactionResponse> post(@RequestBody @Valid TransactionRequest request,
                                                    UriComponentsBuilder uriBuilder) {
       var transaction = transactionService.createTransaction(request);
       var uri = uriBuilder.path("/transaction/{id}").buildAndExpand(transaction.getId()).toUri();

       return ResponseEntity.created(uri).body(new TransactionResponse(transaction));
    }

}
