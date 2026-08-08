package com.authentication.authorization.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserListingRequest {

    private Integer page;
    private Integer size;
    private String sortBy;
    private String sortOrder;

}
