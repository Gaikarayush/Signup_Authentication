package com.authentication.authorization.service;


import com.authentication.authorization.entity.LoginAndLogout;
import com.authentication.authorization.requestDto.LoginAndLogoutRequest;

public interface loginAndLogoutService {
	
	LoginAndLogout addUser(LoginAndLogoutRequest loginAndLogout);
	
}
