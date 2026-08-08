package com.authentication.authorization.controller;

//import com.authentication.authorization.dto.requestDto.AuthenticationSignInRequest;

import com.authentication.authorization.apiResponse.ListingResponseApi;
import com.authentication.authorization.apiResponse.ResponseApi;
import com.authentication.authorization.apiResponse.StatusResponse;
import com.authentication.authorization.dto.request.SignInRequest;
import com.authentication.authorization.dto.request.SignUpRequest;
import com.authentication.authorization.dto.request.UpdateUser;
import com.authentication.authorization.dto.request.UserListingRequest;
import com.authentication.authorization.dto.response.UserResponse;
import com.authentication.authorization.entity.AuthenticationEntity;
import com.authentication.authorization.service.AuthenticationService;
import org.apache.catalina.User;
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
    public ResponseEntity<ListingResponseApi<Page<UserResponse>>> getUsers (@RequestBody UserListingRequest userListingRequest){

        Page<UserResponse> users = authenticationService.getUsers(userListingRequest);

        ListingResponseApi<Page<UserResponse>> response = new ListingResponseApi<>("Users Fetched Successfully", true, users.getTotalElements(), users);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PutMapping("/update/{userId}")
    public ResponseEntity<StatusResponse> updateUser (@PathVariable Integer userId, @RequestBody UpdateUser updateUser){
        UserResponse user = authenticationService.updateUser(userId, updateUser);
        StatusResponse response = new StatusResponse("User updated Successfully", true);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("/delete/{userId}")
    public ResponseEntity<StatusResponse> deleteUser(@PathVariable Integer userId){
        authenticationService.deleteUser(userId);
        StatusResponse response = new StatusResponse("User Deleted Successfully", true);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
