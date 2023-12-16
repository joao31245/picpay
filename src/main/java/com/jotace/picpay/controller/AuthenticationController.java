package com.jotace.picpay.controller;

import com.jotace.picpay.domain.user.User;
import com.jotace.picpay.dto.user.LoginRequest;
import com.jotace.picpay.dto.user.LoginResponse;
import com.jotace.picpay.infra.config.security.TokenService;
import com.jotace.picpay.repositories.UserRepository;
import com.jotace.picpay.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private TokenService tokenService;

    @Autowired
    private UserRepository userRepository;
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody @Valid LoginRequest request) {

        var usernamePassword = new UsernamePasswordAuthenticationToken(request.email(), request.password());

        var authentication =authenticationManager.authenticate(usernamePassword);

        var user = (User) authentication.getPrincipal();

        if(user == null) {
            return ResponseEntity.badRequest().body(new LoginResponse("Deu certo não"));
        }

        var token = tokenService.generateToken(user);

        return ResponseEntity.ok(new LoginResponse(token));
    }
}
