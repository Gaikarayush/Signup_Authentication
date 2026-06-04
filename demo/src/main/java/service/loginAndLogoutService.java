package service;

import entity.LoginAndLogout;
import requestDto.LoginAndLogoutRequest;

public interface loginAndLogoutService {
	
	LoginAndLogout addUser(LoginAndLogoutRequest loginAndLogout);
	
}
