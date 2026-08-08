package com.authentication.authorization.apiResponse;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ResponseApi<T> {
	 private String message;
	 private boolean success;
	 private T data;

	public ResponseApi(String message, boolean success, T data) {
		this.message = message;
		this.success = success;
		this.data = data;
	}

}
