package com.authentication.authorization.dto.response;

import lombok.*;

import java.util.Date;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {

    private Long userId;
    private String firstName;
    private String lastName;
    private String emailId;
    private Long mobileNumber;


}
