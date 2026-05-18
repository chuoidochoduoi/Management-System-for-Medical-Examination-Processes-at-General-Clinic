package org.example.doan2026.Controller;

import jakarta.validation.Valid;
import org.example.doan2026.Model.User;
import org.example.doan2026.Repository.UserRepository;
import org.example.doan2026.Service.AuthService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/auth")
public class ViewController {

    private final AuthService authService;
    private final UserRepository userRepository;

    public ViewController(AuthService authService, UserRepository userRepository) {
        this.authService = authService;
        this.userRepository = userRepository;
    }

    @GetMapping("/page")
    public String showAuthPage() {
        return "auth";
    }

    @PostMapping("/register")
    public String handleRegister(@Valid @ModelAttribute User user, BindingResult result, Model model) {
        String result = authService.registerPatient(user);
        model.addAttribute("message", result);
        return "auth";
    }

    @PostMapping("/login")
    public String handleLogin(@RequestParam String username, @RequestParam String password, Model model) {
        try {
            authService.login(new org.example.doan2026.DTO.request.LoginRequest(username, password));
            var tokenResponse = authService.login(new org.example.doan2026.DTO.request.LoginRequest(username, password));

            User loggedInUser = userRepository.findByUsername(username)
                    .orElseThrow(() -> new RuntimeException("Lỗi hệ thống không tìm thấy hồ sơ!"));

            model.addAttribute("user", loggedInUser);
            model.addAttribute("jwtToken", tokenResponse.getToken());

            return "patient-dashboard";

        } catch (Exception e) {
            model.addAttribute("error", "Đăng nhập thất bại: " + e.getMessage());
            return "auth";
        }
    }
}