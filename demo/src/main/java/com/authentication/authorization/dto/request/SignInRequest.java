package com.authentication.authorization.dto.request;

import com.authentication.authorization.annotation.ValidUserName;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class SignInRequest {

	@Email
	@NotBlank(message = "username is required")
	@ValidUserName
	private String emailId;
	private String password;


	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }
}
