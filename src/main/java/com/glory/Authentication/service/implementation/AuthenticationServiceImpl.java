package com.glory.Authentication.service.implementation;

import com.glory.Authentication.dto.AuthenticationRequest;
import com.glory.Authentication.dto.AuthenticationResponse;
import com.glory.Authentication.dto.RegistrationRequest;
import com.glory.Authentication.dto.RegistrationResponse;
import com.glory.Authentication.enumeration.ProfileStatus;
import com.glory.Authentication.model.Address;
import com.glory.Authentication.model.AppUser;
import com.glory.Authentication.model.Role;
import com.glory.Authentication.reposiotry.AddressRepository;
import com.glory.Authentication.reposiotry.RoleRepository;
import com.glory.Authentication.reposiotry.UserRepository;
import com.glory.Authentication.service.AuthenticationService;
import com.glory.Authentication.service.OtpService;
import com.glory.Authentication.service.TokenService;
import com.glory.Authentication.utlis.BaseResponse;
import com.glory.Authentication.utlis.Response;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.imageio.spi.RegisterableService;
import javax.management.relation.RoleNotFoundException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl  implements AuthenticationService  {


    private final RoleRepository roleRepository;

    private final UserRepository userRepository;

    private final AddressRepository addressRepository;

    private  final AuthenticationManager authenticationManager;

    private final OtpService otpService;

    private final TokenService tokenService;


    private final PasswordEncoder encoder;
    @Override
    @Transactional
    public BaseResponse<String> registerUser(RegistrationRequest registrationRequest) {

        BaseResponse baseResponse = new BaseResponse();
try {
    boolean userAlreadyExist = userRepository.findByEmail(registrationRequest.getEmail()).isPresent();
    Optional<Role> roleAlreadyExist = roleRepository.findByAuthority("USER");


    if (userAlreadyExist) {
       baseResponse.setMessage(Response.EMAIL_ALREADY_EXIST.getResponseMessage());
       baseResponse.setStatusCode(Response.EMAIL_ALREADY_EXIST.getResponseCode());
       return baseResponse;

    }
    if (roleAlreadyExist.isEmpty()) {
        throw  new RoleNotFoundException(String.format("Role not found %s", registrationRequest.getEmail()));
    }
    Address address = new Address();
    address.setCity(registrationRequest.getCity());
    address.setStreetName(registrationRequest.getStreetName());
    address.setZipCode(registrationRequest.getZipCode());
    AppUser newUser = mapToDto(registrationRequest, address);
    addressRepository.save(address);
    userRepository.save(newUser);


    String regOtp = otpService.generateAndSaveActivationToken(newUser);
    baseResponse.setMessage("Successfully registered");
    baseResponse.setStatusCode("200");
    baseResponse.setData(regOtp);

}catch (Exception exception){
   baseResponse.setMessage(exception.getMessage());
}
return  baseResponse;
    }

    @Override
    public BaseResponse<AuthenticationResponse> loginUser(AuthenticationRequest authenticationRequest) {
    AuthenticationResponse authenticationResponse = new AuthenticationResponse();
    BaseResponse baseResponse = new BaseResponse();
        try{
            Authentication auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken
                            (authenticationRequest.getEmail(), authenticationRequest.getPassword())
            );

            String token = tokenService.generateJwt(auth);
        authenticationResponse.setToken(token);
        baseResponse.setStatusCode(Response.SUCCESS.getResponseCode());
        baseResponse.setMessage(Response.SUCCESS.getResponseMessage());
        baseResponse.setData(authenticationResponse);
        } catch(AuthenticationException e){
            baseResponse.setMessage(e.getMessage());
        }

        return baseResponse;

    }

    private AppUser mapToDto(RegistrationRequest registrationRequest,Address address) {
        AppUser user = new AppUser();
        user.setEmail(registrationRequest.getEmail());
        user.setPhoneNumber(registrationRequest.getPhoneNumber());
        user.setFirstName(registrationRequest.getFirstName());
        user.setLastName(registrationRequest.getLastName());
        user.setPassword(encoder.encode(registrationRequest.getPassword()));
        user.setEnable(false);
        user.setStatus(ProfileStatus.NEW);
        user.setAddress(address);
        return  user;

    }
}
