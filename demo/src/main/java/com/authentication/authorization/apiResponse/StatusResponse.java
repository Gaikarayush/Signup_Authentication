package com.authentication.authorization.apiResponse;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StatusResponse {

    private String message;
    private boolean success;

    public StatusResponse(String message, boolean success) {
        this.message = message;
        this.success = success;
    }
}
