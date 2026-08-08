package com.authentication.authorization.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateUser {
    private String firstName;
    private String lastName;
    private Long mobileNumber;
    private String emailId;
    private String password;
    private String confirmPassword;
}
