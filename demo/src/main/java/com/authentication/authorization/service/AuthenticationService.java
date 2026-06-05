package com.authentication.authorization.service;


import com.authentication.authorization.entity.AuthenticationEntity;
import com.authentication.authorization.dto.requestDto.AuthenticationRequest;

public interface AuthenticationService {
	
	AuthenticationEntity addUser(AuthenticationRequest loginAndLogout);
	
}
