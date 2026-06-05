package com.authentication.authorization.implementation;


import com.authentication.authorization.apiResponse.ResponseApi;
import com.authentication.authorization.entity.AuthenticationEntity;
import com.authentication.authorization.repository.AuthenticationRepo;
import com.authentication.authorization.dto.requestDto.AuthenticationRequest;
import com.authentication.authorization.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;


@Service
public class AuthenticationServiceImpl implements AuthenticationService {
	
	@Autowired
	private AuthenticationRepo authenticationRepo;

	@Override
	public AuthenticationEntity addUser(AuthenticationRequest authenticationRequest) {
		// TODO Auto-generated method stub
		String userName = authenticationRequest.getUserName();

		if(userName.contains("@") || userName.matches("\\d{10}")) {
			AuthenticationEntity authenticationEntity = authenticationRepo.findByUserName(userName);
			if (authenticationEntity == null) {
				authenticationEntity = convertToEntity(authenticationRequest);
				authenticationRepo.save(authenticationEntity);
			}else{
				throw new IllegalArgumentException("Username is already taken");
			}
		}

		return null;
	}
	
	
	
	
	public AuthenticationEntity convertToEntity(AuthenticationRequest authenticationRequest) {
		AuthenticationEntity authenticationEntity = new AuthenticationEntity();
		authenticationEntity.setPassword(authenticationRequest.getPassword());
		authenticationEntity.setUserName(authenticationRequest.getUserName());
		authenticationEntity.setCreatedAt(new Date());
		authenticationEntity.setUpdatedAt(new Date());
		authenticationEntity.setUserStatus('Y');
		return authenticationEntity;
	}

}
