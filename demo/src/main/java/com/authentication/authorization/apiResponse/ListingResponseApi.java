package com.authentication.authorization.apiResponse;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ListingResponseApi<T> {

    private String message;
    private boolean success;
    private long totalRecords;
    private T data;

    public ListingResponseApi(String message, boolean success, long totalRecords, T data) {
        this.message = message;
        this.success = success;
        this.totalRecords = totalRecords;
        this.data = data;
    }
}
