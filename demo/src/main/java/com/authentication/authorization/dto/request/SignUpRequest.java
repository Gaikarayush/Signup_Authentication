package com.authentication.authorization.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignUpRequest {

    @NotBlank
    private String firstName;
    private String lastName;
    @NotBlank
    private Long mobileNumber;
    @NotBlank
    private String emailId;
    @NotBlank
    private String password;
    @NotBlank
    private String confirmPassword;


}
