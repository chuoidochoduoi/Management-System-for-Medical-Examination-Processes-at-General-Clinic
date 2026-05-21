package org.example.doan2026.service;

import org.example.doan2026.config.JwtTokenProvider;
import org.example.doan2026.DTO.request.LoginRequest;
import org.example.doan2026.DTO.response.LoginResponse;
import org.example.doan2026.model.User;
import org.example.doan2026.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider tokenProvider;

    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtTokenProvider tokenProvider) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.tokenProvider = tokenProvider;
    }

    public String registerPatient(User user) {
        if (userRepository.existsByUsername(user.getUsername())) {
            return "Tên đăng nhập đã tồn tại trên hệ thống!";
        }



        String encryptedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encryptedPassword);

        user.setRole("ROLE_PATIENT");

        userRepository.save(user);

        return "Đăng ký thông tin bệnh nhân thành công!";
    }

    public LoginResponse login(LoginRequest loginRequest) {
        User user = userRepository.findByUsername(loginRequest.getUsername())
                .orElseThrow(() -> new RuntimeException("Tài khoản không tồn tại!"));

        if (!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
            throw new RuntimeException("Mật khẩu không chính xác!");
        }

        String token = tokenProvider.generateToken(user.getUsername(), user.getRole());
        return LoginResponse.builder()
                .token(token)
                .username(user.getUsername())
                .role(user.getRole())
                .build();
    }
}