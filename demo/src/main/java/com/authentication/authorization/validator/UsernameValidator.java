package com.authentication.authorization.validator;

import com.authentication.authorization.annotation.ValidUserName;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.regex.Pattern;

public class UsernameValidator implements ConstraintValidator<ValidUserName, String> {

    /**
     * Username validator validates the Username is in correct format or not.
     * <p>
     * <p>
     * eg:
     * abc@gmail.com (checks for email validation),
     * 9087654321 (ensures that the mobile number for username is in correct format and also 10 digits)
     * <p>
     * also jakarta validation package provides Constraint Validator Interface
     *
     */

    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");
//    private static final Pattern MOBILE_PATTERN = Pattern.compile("^[0-9]{10}$");


    @Override
    public boolean isValid(String userName, ConstraintValidatorContext context) {

        if (userName == null || userName.trim().isEmpty()) {
            return false;
        }

//        if(userName.matches("\\d+")){
//            return MOBILE_PATTERN.matcher(userName).matches();
//        }


        return EMAIL_PATTERN.matcher(userName).matches();
    }
}
