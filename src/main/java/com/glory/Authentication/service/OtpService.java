package com.glory.Authentication.service;

import com.glory.Authentication.model.AppUser;
import com.glory.Authentication.model.Otp;
import com.glory.Authentication.reposiotry.OtpRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.LocalDateTime;
@Service
@RequiredArgsConstructor
public class OtpService {

    private final OtpRepository repository;

    public String generateAndSaveActivationToken(AppUser newUser) {
        String registrationOtp = generateOtp(6);
        Otp token = new Otp();
        token.setUser(newUser);
        token.setCreatedAt(LocalDateTime.now());
        token.setExpiresAt(LocalDateTime.now().plusSeconds(30));
        token.setOtp(registrationOtp);
        repository.save(token);
        return  registrationOtp;
    }

    private String generateOtp(int optLength) {
        String characters = "0123456789";

        StringBuilder codeBuilder = new StringBuilder();

        SecureRandom secureRandom = new SecureRandom();

        for(int i=0; i<optLength; i++){
            Integer nextCharacter = secureRandom.nextInt(characters.length());

            codeBuilder.append(characters.charAt(nextCharacter));
        }

        return codeBuilder.toString();


    }
}
