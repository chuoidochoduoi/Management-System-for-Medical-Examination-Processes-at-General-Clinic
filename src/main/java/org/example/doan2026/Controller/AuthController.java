package org.example.doan2026.Controller;

import org.example.doan2026.Model.User;
import org.example.doan2026.Service.AuthService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;


    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public String registerPatient(@RequestBody User user) {
        return authService.registerPatient(user);
    }
    @PostMapping("/login")
        public org.example.doan2026.DTO.response.LoginResponse login(
            @RequestBody org.example.doan2026.DTO.request.LoginRequest loginRequest) {
        return authService.login(loginRequest);
    }
}