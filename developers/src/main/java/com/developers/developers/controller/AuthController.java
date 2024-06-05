package com.developers.developers.controller;

import com.developers.developers.Service.AuthService;
import com.developers.developers.model.entity.dto.AuthResponse;
import com.developers.developers.model.entity.dto.LoginRequest;
import com.developers.developers.model.entity.dto.RegisterRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest)
    {
        try
        {
            return ResponseEntity.ok(authService.login(loginRequest));
        } catch (Exception e)
        {
            return ResponseEntity.badRequest().body(new Exception(e.getMessage()));
        }
    }

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest registerRequest)
    {
        return ResponseEntity.ok(authService.register(registerRequest));
    }
}
