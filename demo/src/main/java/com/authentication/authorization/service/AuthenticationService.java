package com.authentication.authorization.service;


import com.authentication.authorization.dto.requestDto.SignInRequest;
import com.authentication.authorization.dto.requestDto.SignUpRequest;
import com.authentication.authorization.entity.AuthenticationEntity;
//import com.authentication.authorization.dto.requestDto.AuthenticationSignInRequest;

public interface AuthenticationService {
	
	AuthenticationEntity userSignIn(SignInRequest loginAndLogout);

	AuthenticationEntity userSignUp(SignUpRequest signUpRequest);
}
