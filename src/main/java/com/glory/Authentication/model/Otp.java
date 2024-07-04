package com.glory.Authentication.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Otp {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String otp;

    private LocalDateTime createdAt;

    private LocalDateTime validatedAt;

    private LocalDateTime  expiresAt;

    @ManyToOne
    @JoinColumn(name = "userId")
    private AppUser user;
}
