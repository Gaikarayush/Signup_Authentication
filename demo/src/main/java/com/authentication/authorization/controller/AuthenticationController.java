package com.authentication.authorization.controller;

//import com.authentication.authorization.dto.requestDto.AuthenticationSignInRequest;

import com.authentication.authorization.apiResponse.ResponseApi;
import com.authentication.authorization.dto.requestDto.SignInRequest;
import com.authentication.authorization.dto.requestDto.SignUpRequest;
import com.authentication.authorization.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/user")
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/signin")
    public ResponseEntity<ResponseApi> login(@RequestBody SignInRequest loginAndLogout) {
//		ResponseEntity responseEntity = new ResponseEntity(null);
        ResponseApi response = null;
        authenticationService.userSignIn(loginAndLogout);
        response = new ResponseApi("User login successfully", true);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/signup")
    public ResponseEntity<ResponseApi> signup(@RequestBody SignUpRequest signUpRequest) {
        ResponseApi responseApi = null;

        try {
            authenticationService.userSignUp(signUpRequest);
        } catch (Exception e) {
            e.printStackTrace();
        }
        responseApi = new ResponseApi("User created successfully", true);

        return ResponseEntity.status(HttpStatus.CREATED).body(responseApi);
    }

}
