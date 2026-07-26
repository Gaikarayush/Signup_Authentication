package com.authentication.authorization.repository;

import com.authentication.authorization.entity.AuthenticationEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface AuthenticationRepo extends JpaRepository<AuthenticationEntity, Long>{
	
	Optional<AuthenticationEntity> findByEmailIdAndUserStatus(String emailId, Character status);
//	AuthenticationEntity findByUserpassword(String userPassword);

	Boolean existsByEmailId(String emailId);

	Page<AuthenticationEntity> findByUserStatus(Character status, Pageable pageable);

}
