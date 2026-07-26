package com.authentication.authorization.exception.handler;

import com.authentication.authorization.apiResponse.ResponseApi;
import com.authentication.authorization.exception.userauthentication.EmailAlreadyExistsException;
import com.authentication.authorization.exception.userauthentication.InvalidCredentialException;
import com.authentication.authorization.exception.userauthentication.PasswordDoesNotMatchException;
import com.authentication.authorization.exception.userauthentication.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ResponseApi<Object>> handleUserNotFoundException(UserNotFoundException ex){
        ResponseApi<Object> response = new ResponseApi<>(ex.getMessage(), false, null);

     return ResponseEntity.status(HttpStatus.NOT_FOUND)
            .body(response);
    }

    @ExceptionHandler(InvalidCredentialException.class)
    public ResponseEntity<ResponseApi<Object>> handleInvalidCredentialException(InvalidCredentialException ex){
        ResponseApi<Object> response = new ResponseApi<>(ex.getMessage(), false, null);
        return  ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(response);
    }

    @ExceptionHandler(EmailAlreadyExistsException.class)
    public ResponseEntity<ResponseApi<Object>> handleEmailAlreadyExistsException(EmailAlreadyExistsException ex){
        ResponseApi<Object> response = new ResponseApi<>(ex.getMessage(), false, null);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(PasswordDoesNotMatchException.class)
    public ResponseEntity<ResponseApi<Object>> handlePasswordDoesNotMatchException(PasswordDoesNotMatchException ex){
        ResponseApi<Object> response = new ResponseApi<>(ex.getMessage(), false, null);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

}
