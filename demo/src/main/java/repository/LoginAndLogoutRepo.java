package repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import entity.LoginAndLogout;

@Repository
public interface LoginAndLogoutRepo extends JpaRepository<LoginAndLogout, Long>{
	
	LoginAndLogout findByUserNameOrEmailIdAndPassword(String userName, String emailId, String password);
	
}
