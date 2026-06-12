package com.authentication.authorization.implementation;

import static org.springframework.util.StringUtils.*;

import com.authentication.authorization.dto.requestDto.SignInRequest;
import com.authentication.authorization.dto.requestDto.SignUpRequest;
import com.authentication.authorization.entity.AuthenticationEntity;
import com.authentication.authorization.repository.AuthenticationRepo;
//import com.authentication.authorization.dto.requestDto.AuthenticationSignInRequest;
import com.authentication.authorization.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;


@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    @Autowired
    private AuthenticationRepo authenticationRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public AuthenticationEntity userSignIn(SignInRequest signInRequest) {
        // TODO Auto-generated method stub
        String loginEmailId = signInRequest.getEmailId();

//		if(emailId.contains("@") || emailId.matches("\\d{10}")) {if(loginEmailId.contains("@")) {
        AuthenticationEntity authenticationEntity =
                authenticationRepo.findByEmailIdAndUserStatus(loginEmailId, 'Y')
                        .orElseThrow(() ->
                                new IllegalArgumentException("No such user found, please Sign-up")
                        );

        String userPassword = authenticationEntity.getPassword();

        boolean isPasswordValid = passwordEncoder.matches(signInRequest.getPassword(), userPassword);

        if(!isPasswordValid){
            throw new IllegalArgumentException("Invalid Credentials");
        }
        return null;

    }


    public AuthenticationEntity userSignUp(SignUpRequest signUpRequest) {

        boolean exists = false;
        if (hasText(signUpRequest.getEmailId())) {
            exists = authenticationRepo.existsByEmailId(signUpRequest.getEmailId());
        }
        if (exists) {
            throw new IllegalArgumentException("Email id already exists");
        }
        if (hasText(signUpRequest.getConfirmPassword()) &&
                hasText(signUpRequest.getPassword()) &&
                !signUpRequest.getConfirmPassword().equals(signUpRequest.getPassword())) {
            throw new IllegalArgumentException("Password is not matching");
        }

        AuthenticationEntity entity = convertToSignUpEntity(signUpRequest);

        return authenticationRepo.save(entity);
    }


    private AuthenticationEntity convertToSignUpEntity(SignUpRequest signUpRequest) {
        return AuthenticationEntity.builder()
                .firstName(signUpRequest.getFirstName())
                .lastName(signUpRequest.getLastName())
                .mobileNumber(signUpRequest.getMobileNumber())
                .password(passwordEncoder.encode(signUpRequest.getPassword()))
                .emailId(signUpRequest.getEmailId())
                .createdAt(new Date())
                .updatedAt(new Date())
                .userStatus('Y')
                .build();
    }

}
