package com.authentication.authorization.repository;

import com.authentication.authorization.entity.LoginAndLogout;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface LoginAndLogoutRepo extends JpaRepository<LoginAndLogout, Long>{
	
	LoginAndLogout findByUserNameOrEmailIdAndPassword(String userName, String emailId, String password);
	
}
