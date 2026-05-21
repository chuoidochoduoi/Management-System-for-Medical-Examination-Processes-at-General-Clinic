package org.example.doan2026.controller;

import org.example.doan2026.DTO.response.ApiResponse;
import org.example.doan2026.DTO.response.LoginResponse;
import org.example.doan2026.model.User;
import org.example.doan2026.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;


    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<String>> registerPatient(@RequestBody User user) {
        String result = authService.registerPatient(user);
        return ResponseEntity.ok(ApiResponse.success("Đăng ký thành công", result));
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<LoginResponse>> login(
            @RequestBody org.example.doan2026.DTO.request.LoginRequest loginRequest) {
        LoginResponse result = authService.login(loginRequest);
        return ResponseEntity.ok(ApiResponse.success("Đăng nhập thành công", result));
    }
}