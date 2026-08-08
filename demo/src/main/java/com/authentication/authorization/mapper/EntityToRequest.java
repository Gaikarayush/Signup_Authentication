package com.authentication.authorization.mapper;

import com.authentication.authorization.dto.response.UserResponse;
import com.authentication.authorization.entity.AuthenticationEntity;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class EntityToRequest {

    public static UserResponse mapToUserResponse(AuthenticationEntity signinEntity) {
        return UserResponse.builder()
                .userId(signinEntity.getUserId())
                .firstName(signinEntity.getFirstName())
                .lastName(signinEntity.getLastName())
                .mobileNumber(signinEntity.getMobileNumber())
                .emailId(signinEntity.getEmailId())
                .build();
    }

}
