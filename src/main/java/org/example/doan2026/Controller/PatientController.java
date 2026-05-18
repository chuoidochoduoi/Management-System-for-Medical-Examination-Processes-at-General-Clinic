package org.example.doan2026.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/patient")
public class PatientController {

    @GetMapping("/profile")
    public String getPatientProfile() {
        return "Chào mừng Bệnh nhân! Đây là dữ liệu hồ sơ bệnh án tuyệt mật của bạn.";
    }
}