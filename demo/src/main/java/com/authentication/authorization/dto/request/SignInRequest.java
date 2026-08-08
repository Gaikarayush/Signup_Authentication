package com.authentication.authorization.dto.request;

import com.authentication.authorization.annotation.ValidUserName;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignInRequest {

	@Email
	@NotBlank(message = "username is required")
	@ValidUserName
	private String emailId;
	private String password;

}
