package com.authentication.authorization.controller;

//import com.authentication.authorization.dto.requestDto.AuthenticationSignInRequest;

import com.authentication.authorization.apiResponse.ResponseApi;
import com.authentication.authorization.dto.request.SignInRequest;
import com.authentication.authorization.dto.request.SignUpRequest;
import com.authentication.authorization.dto.request.UserListingRequest;
import com.authentication.authorization.dto.response.UserResponse;
import com.authentication.authorization.entity.AuthenticationEntity;
import com.authentication.authorization.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/user")
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/signin")
    public ResponseEntity<ResponseApi<UserResponse>> login(@RequestBody SignInRequest loginAndLogout) {

        UserResponse user = authenticationService.userSignIn(loginAndLogout);
            ResponseApi<UserResponse> response = new ResponseApi<>("User login successfully", true, user);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/signup")
    public ResponseEntity<ResponseApi<UserResponse>> signup(@RequestBody SignUpRequest signUpRequest) {

        UserResponse user = authenticationService.userSignUp(signUpRequest);

         ResponseApi<UserResponse> responseApi = new ResponseApi<>("User created successfully", true, user);

        return ResponseEntity.status(HttpStatus.CREATED).body(responseApi);
    }


    @PostMapping("/listing")
    public ResponseEntity<ResponseApi<Page<AuthenticationEntity>>> getUsers (@RequestBody UserListingRequest userListingRequest){

        Page<AuthenticationEntity> users = authenticationService.getUsers(userListingRequest);

        ResponseApi<Page<AuthenticationEntity>> response = new ResponseApi<>("Users Fetched Successfully", true, users);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

}
