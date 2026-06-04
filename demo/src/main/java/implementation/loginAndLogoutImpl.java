package implementation;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import entity.LoginAndLogout;
import repository.LoginAndLogoutRepo;
import requestDto.LoginAndLogoutRequest;
import service.loginAndLogoutService;

@Service
public class loginAndLogoutImpl implements loginAndLogoutService{
	
	@Autowired
	private LoginAndLogoutRepo loginAndLogoutRepo;

	@Override
	public LoginAndLogout addUser(LoginAndLogoutRequest loginAndLogoutRequest) {
		// TODO Auto-generated method stub
		String userName = loginAndLogoutRequest.getUserName();
		String password = loginAndLogoutRequest.getPassword();
		Long mobileNumber = loginAndLogoutRequest.getMobileNumber();
		String emailId = loginAndLogoutRequest.getEmailId();
		
		LoginAndLogout find = loginAndLogoutRepo.findByUserNameOrEmailIdAndPassword(userName != null? userName : null, emailId != null? emailId:null, password);
		
		if(find!=null) {
			LoginAndLogout save = convertToEntity(loginAndLogoutRequest);
			loginAndLogoutRepo.save(save);
		}else {
			LoginAndLogout save = convertToEntity(loginAndLogoutRequest);
			loginAndLogoutRepo.save(save);
		}
		
		return null;
	}
	
	
	
	
	private LoginAndLogout convertToEntity(LoginAndLogoutRequest loginAndLogoutRequest) {
		LoginAndLogout loginAndLogout = new LoginAndLogout();
		loginAndLogout.setEmailId(loginAndLogoutRequest.getEmailId());
		loginAndLogout.setMobileNumber(loginAndLogoutRequest.getMobileNumber());
		loginAndLogout.setPassword(loginAndLogout.getPassword());
		loginAndLogout.setUserName(loginAndLogoutRequest.getUserName());
		return loginAndLogout;
	}

}
