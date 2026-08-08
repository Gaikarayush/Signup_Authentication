package com.authentication.authorization.implementation;

import static org.springframework.util.StringUtils.*;

import com.authentication.authorization.dto.request.SignInRequest;
import com.authentication.authorization.dto.request.SignUpRequest;
import com.authentication.authorization.dto.request.UserListingRequest;
import com.authentication.authorization.dto.request.UpdateUser;
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
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Optional;


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
    public Page<UserResponse> getUsers(UserListingRequest userListingRequest) {
        String sort = userListingRequest.getSortBy();
        if(userListingRequest.getSortBy().isEmpty()){
            sort = "userId";
        }
        Pageable pageable = PageRequest.of(userListingRequest.getPage()-1, userListingRequest.getSize(), Sort.by(sort));

        Page<AuthenticationEntity> entity =  authenticationRepo.findByUserStatus('Y', pageable);
        return entity.map(authenticationEntity -> UserResponse.builder()
                .userId(authenticationEntity.getUserId())
                .firstName(authenticationEntity.getFirstName())
                .lastName(authenticationEntity.getLastName())
                .mobileNumber(authenticationEntity.getMobileNumber())
                .emailId(authenticationEntity.getEmailId())
                .build());
    }

    @Override
    public UserResponse updateUser(Integer userId, UpdateUser updateUser) {

        Optional<AuthenticationEntity> user =
                authenticationRepo.findByUserIdAndUserStatus(userId, 'Y');

        if (user.isEmpty()) {
            throw new UserNotFoundException("No such user found");
        }

        AuthenticationEntity existingUser = user.get();
        if(updateUser.getFirstName()!=null && !updateUser.getFirstName().isEmpty()){
            existingUser.setFirstName(updateUser.getFirstName());
        }
        if(updateUser.getLastName()!=null && !updateUser.getLastName().isEmpty()){
            existingUser.setLastName(updateUser.getLastName());
        }
        if(updateUser.getEmailId()!=null && !updateUser.getEmailId().isEmpty()){
            existingUser.setEmailId(updateUser.getEmailId());
        }
        if((updateUser.getPassword()!=null && !updateUser.getPassword().isEmpty()) && (updateUser.getConfirmPassword()!=null && !updateUser.getConfirmPassword().isEmpty())){
            if(updateUser.getPassword().equals(updateUser.getConfirmPassword())){
                existingUser.setPassword(updateUser.getPassword());
            }else{
                throw new PasswordDoesNotMatchException("Password does not Match");
            }
        }

        existingUser.setUpdatedAt(LocalDateTime.now());
        if(updateUser.getMobileNumber()!=null && updateUser.getMobileNumber() > 0){
            existingUser.setMobileNumber(updateUser.getMobileNumber());
        }

        AuthenticationEntity updatedUser =
                authenticationRepo.save(existingUser);

        return EntityToRequest.mapToUserResponse(updatedUser);
    }

    @Override
    public void deleteUser(Integer userId) {

        if (userId == null) {
            throw new UserNotFoundException("No such user found");
        }

        Optional<AuthenticationEntity> user =
                authenticationRepo.findByUserIdAndUserStatus(userId, 'Y');

        if (user.isEmpty()) {
            throw new UserNotFoundException("No such user found");
        }

        AuthenticationEntity existingUser = user.get();
        existingUser.setUserStatus('N');
        existingUser.setUpdatedAt(LocalDateTime.now());
        authenticationRepo.save(existingUser);

    }


}
