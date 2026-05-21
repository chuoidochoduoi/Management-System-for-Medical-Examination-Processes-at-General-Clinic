package org.example.doan2026.model;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "profiles")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder

public class Profile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String email;

    private String fullname;
    private String phoneNumber;
    private LocalDate dateOfBirth;
    private String gender;
    private String address;

    @OneToOne(mappedBy = "profile", fetch = FetchType.LAZY)
    private User user;
}
