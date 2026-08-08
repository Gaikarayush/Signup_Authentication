package com.authentication.authorization.service;


import com.authentication.authorization.dto.request.SignInRequest;
import com.authentication.authorization.dto.request.SignUpRequest;
import com.authentication.authorization.dto.request.UserListingRequest;
import com.authentication.authorization.dto.request.UpdateUser;
import com.authentication.authorization.dto.response.UserResponse;
import com.authentication.authorization.entity.AuthenticationEntity;
import org.springframework.data.domain.Page;
//import com.authentication.authorization.dto.requestDto.AuthenticationSignInRequest;

public interface AuthenticationService {

	UserResponse userSignIn(SignInRequest loginAndLogout);

	UserResponse userSignUp(SignUpRequest signUpRequest);


	//Need to create an proper pagination logic to remove unnecessary Page MetaData and also
	//Create an Request to build listing in which proper page, size, etc fields will present...... !!!!!!
	Page<AuthenticationEntity> getUsers(UserListingRequest userListingRequest);

	UserResponse updateUser(Integer userId, UpdateUser updateUser);
}
