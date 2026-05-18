package org.example.doan2026.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false, length = 50)
    @NotBlank(message = "Tài khoản không được để trống")
    @Pattern(regexp = "^[a-zA-Z0-9_]{3,20}$", message = "Tài khoản phải từ 3-20 ký tự, không chứa dấu cách hoặc ký tự đặc biệt")
    private String username;

    @Column(nullable = false)
    @NotBlank(message = "Mật khẩu không được để trống")
    @Size(min = 6, message = "Mật khẩu phải từ 6 ký tự trở lên")
    private String password;

    @Column(unique = true, nullable = false, length = 100)
    @NotBlank(message = "Email không được để trống")
    @Email(message = "Định dạng Email không hợp lệ (ví dụ: msme@gmail.com)")
    private String email;

    @Column(name = "full_name", nullable = false, length = 100)
    @NotBlank(message = "Họ và tên không được để trống")
    @Pattern(regexp = "^[\\p{L}\\s]+$", message = "Họ và tên chỉ được chứa chữ cái và khoảng trắng (không chứa số)")
    private String fullName;

    @Column(name = "phone_number", length = 15)
    @Pattern(regexp = "^(0[3|5|7|8|9])+([0-9]{8})$", message = "Số điện thoại không hợp lệ (phải bắt đầu bằng số 0 và đủ 10 số)")
    private String phoneNumber;

    @Column(name = "date_of_birth")
    @NotNull(message = "Ngày sinh không được để trống")
    @Past(message = "Ngày sinh phải là một ngày trong quá khứ")
    private LocalDate dateOfBirth;

    @Column(length = 10)
    @NotBlank(message = "Vui lòng chọn giới tính")
    private String gender;

    @Column(columnDefinition = "TEXT")
    @NotBlank(message = "Địa chỉ không được để trống")
    private String address;

    @Column(nullable = false, length = 30)
    private String role; // Mặc định lưu: "ROLE_PATIENT"

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }
}