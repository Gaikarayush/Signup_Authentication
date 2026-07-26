package com.authentication.authorization.exception.userauthentication;

public class PasswordDoesNotMatchException extends RuntimeException{

    public PasswordDoesNotMatchException(String message){
        super(message);
    }
}
