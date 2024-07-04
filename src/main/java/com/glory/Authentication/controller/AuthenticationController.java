package com.glory.Authentication.controller;

import com.glory.Authentication.dto.AuthenticationRequest;
import com.glory.Authentication.dto.AuthenticationResponse;
import com.glory.Authentication.dto.RegistrationRequest;
import com.glory.Authentication.service.AuthenticationService;
import com.glory.Authentication.utlis.BaseResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/auth")
@RequiredArgsConstructor
@CrossOrigin("*")
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping(value = "/register",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BaseResponse<String>> registerUser(@RequestBody @Valid RegistrationRequest registrationRequest){
        return  ResponseEntity.ok(authenticationService.registerUser(registrationRequest));

    }

    @PostMapping(value = "/user", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BaseResponse<AuthenticationResponse>> loginUser(@RequestBody @Valid AuthenticationRequest authenticationRequest){
      return ResponseEntity.ok(authenticationService.loginUser(authenticationRequest));
    }

}
