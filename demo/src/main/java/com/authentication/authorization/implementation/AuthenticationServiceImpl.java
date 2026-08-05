package com.authentication.authorization.implementation;

import static org.springframework.util.StringUtils.*;

import com.authentication.authorization.dto.request.SignInRequest;
import com.authentication.authorization.dto.request.SignUpRequest;
import com.authentication.authorization.dto.request.UserListingRequest;
import com.authentication.authorization.dto.response.UserResponse;
import com.authentication.authorization.entity.AuthenticationEntity;
import com.authentication.authorization.exception.userauthentication.EmailAlreadyExistsException;
import com.authentication.authorization.exception.userauthentication.InvalidCredentialException;
import com.authentication.authorization.exception.userauthentication.PasswordDoesNotMatchException;
import com.authentication.authorization.exception.userauthentication.UserNotFoundException;
import com.authentication.authorization.mapper.EntityToRequest;
import com.authentication.authorization.mapper.RequestToEntity;
import com.authentication.authorization.repository.AuthenticationRepo;
//import com.authentication.authorization.dto.requestDto.AuthenticationSignInRequest;
import com.authentication.authorization.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    @Autowired
    private AuthenticationRepo authenticationRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserResponse userSignIn(SignInRequest signInRequest) {
        // TODO Auto-generated method stub

        String loginEmailId = signInRequest.getEmailId();

//		if(emailId.contains("@") || emailId.matches("\\d{10}")) {if(loginEmailId.contains("@")) {
        AuthenticationEntity authenticationEntity =
                authenticationRepo.findByEmailIdAndUserStatus(loginEmailId, 'Y')
                        .orElseThrow(() ->
                                new UserNotFoundException("No such user found, please Sign-up")
                        );

        String userPassword = authenticationEntity.getPassword();

        boolean isPasswordValid = passwordEncoder.matches(signInRequest.getPassword(), userPassword);

        if(!isPasswordValid){
            throw new InvalidCredentialException("Invalid Credentials");
        }
        return EntityToRequest.mapToUserResponse(authenticationEntity);

    }


    public UserResponse userSignUp(SignUpRequest signUpRequest) {

        boolean exists = false;
        if (hasText(signUpRequest.getEmailId())) {
            exists = authenticationRepo.existsByEmailId(signUpRequest.getEmailId());
        }
        if (exists) {
            throw new EmailAlreadyExistsException("Email id already exists");
        }
        if (hasText(signUpRequest.getConfirmPassword()) &&
                hasText(signUpRequest.getPassword()) &&
                !signUpRequest.getConfirmPassword().equals(signUpRequest.getPassword())) {
            throw new PasswordDoesNotMatchException("Password is not matching");
        }

        AuthenticationEntity entity = RequestToEntity.mapToAuthenticationEntity(signUpRequest);
        AuthenticationEntity savedEntity = authenticationRepo.save(entity);
        return EntityToRequest.mapToUserResponse(savedEntity);
    }

    //Need to create an proper pagination logic to remove unnecessary Page MetaData and also
    //Create an Request to build listing in which proper page, size, etc fields will present...... !!!!!!
    @Override
    public Page<AuthenticationEntity> getUsers(UserListingRequest userListingRequest) {
        String sort = userListingRequest.getSortBy();
        if(userListingRequest.getSortBy().isEmpty()){
            sort = "userId";
        }
        Pageable pageable = PageRequest.of(userListingRequest.getPage()-1, userListingRequest.getSize(), Sort.by(sort));

        return authenticationRepo.findByUserStatus('Y', pageable);
    }








}
