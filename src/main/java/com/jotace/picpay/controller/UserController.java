package com.jotace.picpay.controller;

import com.jotace.picpay.domain.user.User;
import com.jotace.picpay.dto.UserRequest;
import com.jotace.picpay.dto.UserResponse;
import com.jotace.picpay.service.UserService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService service;

    @PostMapping
    @Transactional
    public ResponseEntity<UserResponse> post(@RequestBody @Valid UserRequest request, UriComponentsBuilder uriBuilder) {
        var user = new User(request);
        service.save(user);
        var uri = uriBuilder.path("/user/{id}").buildAndExpand(user.getId()).toUri();
        return ResponseEntity.created(uri).body(new UserResponse(user));
    }
    @GetMapping
    public ResponseEntity<List<UserResponse>> get() {
        var users = service.getAllUsers().stream().map(UserResponse::new).toList();

        return ResponseEntity.ok(users);
    }

}
