package com.authentication.authorization.repository;

import com.authentication.authorization.entity.AuthenticationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface AuthenticationRepo extends JpaRepository<AuthenticationEntity, Long>{
	
	AuthenticationEntity findByUserName(String userName);
	AuthenticationEntity findByUserpassword(String userPassword);

}
