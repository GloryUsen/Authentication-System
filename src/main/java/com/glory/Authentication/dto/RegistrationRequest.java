package com.glory.Authentication.dto;

import com.glory.Authentication.model.Address;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.Instant;

@Data
public class RegistrationRequest {

    private String email;
    private String password;
    private  String firstName;
    private String lastName;
    private String phoneNumber;
    private String streetName;

    private String zipCode;

    private String city;

}
