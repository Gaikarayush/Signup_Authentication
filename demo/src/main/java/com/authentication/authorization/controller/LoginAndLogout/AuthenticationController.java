package com.authentication.authorization.controller.LoginAndLogout;

//import com.authentication.authorization.dto.requestDto.AuthenticationSignInRequest;
import com.authentication.authorization.apiResponse.ResponseApi;
import com.authentication.authorization.dto.requestDto.AuthenticationSignUpRequest;
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
	
//	@PostMapping("/login")
//	public ResponseEntity<ResponseApi> login(@RequestBody AuthenticationSignInRequest loginAndLogout){
////		ResponseEntity responseEntity = new ResponseEntity(null);
//		ResponseApi response = null;
//		try{
//			authenticationService.addUser(loginAndLogout);
//			response = new ResponseApi("User Registered Successfully", true);
//		}catch (Exception e){
//			e.printStackTrace();
//		}
//
//		return ResponseEntity.status(HttpStatus.OK).body(response);
//	}

	@PostMapping("/signup")
	public ResponseEntity<ResponseApi> signup(@RequestBody AuthenticationSignUpRequest authenticationSignUpRequest){
		ResponseApi responseApi = null;

		try{
			authenticationService.userSignUp(authenticationSignUpRequest);
		}catch (Exception e){
			e.printStackTrace();
		}

		return ResponseEntity.status(HttpStatus.CREATED).body(responseApi);
	}

}
