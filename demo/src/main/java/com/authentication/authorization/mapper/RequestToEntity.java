package com.authentication.authorization.mapper;

import com.authentication.authorization.dto.request.SignUpRequest;
import com.authentication.authorization.entity.AuthenticationEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;
import java.util.Date;

public class RequestToEntity {



    public static AuthenticationEntity mapToAuthenticationEntity(SignUpRequest signUpRequest) {
        return AuthenticationEntity.builder()
                .firstName(signUpRequest.getFirstName())
                .lastName(signUpRequest.getLastName())
                .mobileNumber(signUpRequest.getMobileNumber())
                .emailId(signUpRequest.getEmailId())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .userStatus('Y')
                .build();
    }

}
