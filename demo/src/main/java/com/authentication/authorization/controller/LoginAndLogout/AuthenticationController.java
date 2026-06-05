package com.authentication.authorization.controller.LoginAndLogout;

import com.authentication.authorization.dto.requestDto.AuthenticationRequest;
import com.authentication.authorization.apiResponse.ResponseApi;
import com.authentication.authorization.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/registration")
public class AuthenticationController {
	
	@Autowired
	private AuthenticationService AuthenticationService;
	
	@PostMapping("/login")
	public ResponseEntity<ResponseApi> login(@RequestBody AuthenticationRequest loginAndLogout){
//		ResponseEntity responseEntity = new ResponseEntity(null);
		ResponseApi response = null;
		try{
			AuthenticationService.addUser(loginAndLogout);
			response = new ResponseApi("User Registered Successfully", true);
		}catch (Exception e){
			e.printStackTrace();
		}

		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}

}
