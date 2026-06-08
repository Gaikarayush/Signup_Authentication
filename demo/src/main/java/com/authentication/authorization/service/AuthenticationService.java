package com.authentication.authorization.service;


import com.authentication.authorization.dto.requestDto.AuthenticationSignUpRequest;
import com.authentication.authorization.entity.AuthenticationEntity;
//import com.authentication.authorization.dto.requestDto.AuthenticationSignInRequest;

public interface AuthenticationService {
	
//	AuthenticationEntity addUser(AuthenticationSignInRequest loginAndLogout);

	AuthenticationEntity userSignUp(AuthenticationSignUpRequest authenticationSignUpRequest);
}
