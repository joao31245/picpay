package com.jotace.picpay.controller;

import com.jotace.picpay.dto.TransactionRequest;
import com.jotace.picpay.dto.TransactionResponse;
import com.jotace.picpay.service.TransactionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@RequestMapping(value = "transaction", produces = "application/json")
@Tag(name = "Pic Pay simplified")
public class TransactionController {
    @Autowired
    private TransactionService transactionService;


    @PostMapping
    @Transactional
    @Operation(summary = "Create a transaction", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Worked!"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    public ResponseEntity<TransactionResponse> post(@RequestBody @Valid TransactionRequest request,
                                                    UriComponentsBuilder uriBuilder) {
       var transaction = transactionService.createTransaction(request);
       var uri = uriBuilder.path("/transaction/{id}").buildAndExpand(transaction.getId()).toUri();

       return ResponseEntity.created(uri).body(new TransactionResponse(transaction));
    }

}
