package com.jotace.picpay.controller;

import com.jotace.picpay.domain.user.User;
import com.jotace.picpay.dto.user.DeleteRequest;
import com.jotace.picpay.dto.user.UpdateRequest;
import com.jotace.picpay.dto.user.UserRequest;
import com.jotace.picpay.dto.user.UserResponse;
import com.jotace.picpay.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping(value = "user", produces = ("application/json"))
@Tag(name = "Pic Pay simplified")
public class UserController {
    @Autowired
    private UserService service;

    @PostMapping
    @Transactional
    @Operation(summary = "Create a new User", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Worked!"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    public ResponseEntity<UserResponse> post(@RequestBody @Valid UserRequest request, UriComponentsBuilder uriBuilder) {
        var user = new User(request);
        service.save(user);
        var uri = uriBuilder.path("/user/{id}").buildAndExpand(user.getId()).toUri();
        return ResponseEntity.created(uri).body(new UserResponse(user));
    }
    @GetMapping
    @Operation(summary = "Push all users from database.", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Worked!"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    public ResponseEntity<List<UserResponse>> get() {
        var users = service.getAllUsers().stream().map(UserResponse::new).toList();

        return ResponseEntity.ok(users);
    }

    @DeleteMapping
    @Operation(summary = "Delete specific user from database", method = "Delete")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Worked!"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error"),
            @ApiResponse(responseCode = "204", description = "No content")
    })
    public ResponseEntity delete(@RequestBody DeleteRequest request) {
        service.delete(request);
        return ResponseEntity.noContent().build();
    }

    @PutMapping
    @Operation(summary = "Update specific user only accepting alters in email and password")
    public ResponseEntity<UserResponse> update(@RequestBody UpdateRequest request){
        return ResponseEntity.ok(service.update(request));
    }
}
