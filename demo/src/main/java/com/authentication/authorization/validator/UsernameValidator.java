package com.authentication.authorization.validator;

import com.authentication.authorization.annotation.ValidUserName;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.regex.Pattern;

public class UsernameValidator implements ConstraintValidator<ValidUserName, String> {

    private static final Pattern MOBILE_PATTERN = Pattern.compile("^[0-9]{10}$");

    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");


    @Override
    public boolean isValid(String userName, ConstraintValidatorContext context) {

        if(userName == null || userName.trim().isEmpty()){
            return false;
        }

        if(userName.matches("\\d+")){
            return MOBILE_PATTERN.matcher(userName).matches();
        }


        return EMAIL_PATTERN.matcher(userName).matches();
    }
}
