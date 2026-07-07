package br.com.hamburgueria_local.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.hamburgueria_local.dto.request.AuthLoginRequest;
import br.com.hamburgueria_local.dto.request.AuthRegisterRequest;
import br.com.hamburgueria_local.dto.response.AuthResponse;
import br.com.hamburgueria_local.services.AuthService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService service;

    public AuthController(AuthService service) {
        this.service = service;
    }

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> registrar(@Valid @RequestBody AuthRegisterRequest request) {
        return ResponseEntity.ok(service.registrar(request));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody AuthLoginRequest request) {
        return ResponseEntity.ok(service.login(request));
    }
}
