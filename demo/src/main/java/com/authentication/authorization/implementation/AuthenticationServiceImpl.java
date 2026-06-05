package com.authentication.authorization.implementation;


import com.authentication.authorization.dto.requestDto.AuthenticationSignUpRequest;
import com.authentication.authorization.entity.AuthenticationEntity;
import com.authentication.authorization.repository.AuthenticationRepo;
//import com.authentication.authorization.dto.requestDto.AuthenticationSignInRequest;
import com.authentication.authorization.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;


@Service
public class AuthenticationServiceImpl implements AuthenticationService {
	
	@Autowired
	private AuthenticationRepo authenticationRepo;

//	@Override
//	public AuthenticationEntity addUser(AuthenticationSignInRequest authenticationSignInRequest) {
//		// TODO Auto-generated method stub
//		String userName = authenticationSignInRequest.getUserName();
//
//		if(userName.contains("@") || userName.matches("\\d{10}")) {
//			AuthenticationEntity authenticationEntity = authenticationRepo.findByUserName(userName);
//			if (authenticationEntity == null) {
////				authenticationEntity = convertToSignInEntity(authenticationSignInRequest);
//				authenticationRepo.save(authenticationEntity);
//			}else{
//				throw new IllegalArgumentException("Username is already taken");
//			}
//		}
//
//		return null;
//	}


	public AuthenticationEntity userSignUp(AuthenticationSignUpRequest authenticationSignUpRequest){

		boolean exists = false;
		if(authenticationSignUpRequest.getEmailId()!=null && !authenticationSignUpRequest.getEmailId().isEmpty()){
			exists = authenticationRepo.existsByEmailId(authenticationSignUpRequest.getEmailId());
		}
		if(exists){
			throw new IllegalArgumentException("Email id already exists");
		}
		if((authenticationSignUpRequest.getConfirmPassword()!=null && !authenticationSignUpRequest.getConfirmPassword().isEmpty()) && !authenticationSignUpRequest.getConfirmPassword().equals(authenticationSignUpRequest.getPassword())){
			throw new IllegalArgumentException("Password is not matching");
		}

		AuthenticationEntity entity = convertToSignUpEntity(authenticationSignUpRequest);

		return authenticationRepo.save(entity);
	}
	
	
	
//	private AuthenticationEntity convertToSignInEntity(AuthenticationSignInRequest authenticationSignInRequest) {
//		AuthenticationEntity authenticationEntity = new AuthenticationEntity();
//		authenticationEntity.setPassword(authenticationSignInRequest.getPassword());
//		authenticationEntity.setCreatedAt(new Date());
//		authenticationEntity.setUpdatedAt(new Date());
//		authenticationEntity.setUserStatus('Y');
//		return authenticationEntity;
//	}

	private AuthenticationEntity convertToSignUpEntity(AuthenticationSignUpRequest authenticationSignUpRequest){
		AuthenticationEntity authenticationEntity = new AuthenticationEntity();
		authenticationEntity.setPassword(authenticationSignUpRequest.getPassword());
		authenticationEntity.setFirstName(authenticationSignUpRequest.getFirstName());
		authenticationEntity.setEmailId(authenticationSignUpRequest.getEmailId());
		authenticationEntity.setLastName(authenticationEntity.getLastName());
		authenticationEntity.setMobileNumber(authenticationEntity.getMobileNumber());
		authenticationEntity.setCreatedAt(new Date());
		authenticationEntity.setUpdatedAt(new Date());
		authenticationEntity.setUserStatus('Y');
		return authenticationEntity;
	}

}
