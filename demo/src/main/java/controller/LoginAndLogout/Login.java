package controller.LoginAndLogout;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import requestDto.LoginAndLogoutRequest;
import responseData.ResponseApi;

import service.loginAndLogoutService;

@RestController
@RequestMapping("/registration")
public class Login {
	
	@Autowired
	private loginAndLogoutService loginAndLogoutService;
	
	@PostMapping("/login")
	public ResponseEntity<ResponseApi> login(@RequestBody LoginAndLogoutRequest loginAndLogout){
//		ResponseEntity responseEntity = new ResponseEntity(null);
		loginAndLogoutService.addUser(loginAndLogout);
		ResponseApi response = new ResponseApi("User Registered Successfully", true);
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}

}
