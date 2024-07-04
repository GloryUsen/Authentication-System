package com.glory.Authentication.service;

import com.glory.Authentication.dto.AuthenticationRequest;
import com.glory.Authentication.dto.AuthenticationResponse;
import com.glory.Authentication.dto.RegistrationRequest;
import com.glory.Authentication.dto.RegistrationResponse;
import com.glory.Authentication.utlis.BaseResponse;

public interface AuthenticationService {

    BaseResponse<String>  registerUser(RegistrationRequest registrationRequest);

    BaseResponse<AuthenticationResponse> loginUser(AuthenticationRequest authenticationRequest);
}
