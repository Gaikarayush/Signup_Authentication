package com.authentication.authorization.service;


import com.authentication.authorization.dto.request.SignInRequest;
import com.authentication.authorization.dto.request.SignUpRequest;
import com.authentication.authorization.dto.request.UpdateUser;
import com.authentication.authorization.dto.response.UserResponse;
import com.authentication.authorization.entity.AuthenticationEntity;
import org.springframework.data.domain.Page;
//import com.authentication.authorization.dto.requestDto.AuthenticationSignInRequest;

public interface AuthenticationService {

	UserResponse userSignIn(SignInRequest loginAndLogout);

	UserResponse userSignUp(SignUpRequest signUpRequest);

	Page<AuthenticationEntity> getUsers(Integer page, Integer size, String sortBy);

	UserResponse updateUser(Integer userId, UpdateUser updateUser);
}
