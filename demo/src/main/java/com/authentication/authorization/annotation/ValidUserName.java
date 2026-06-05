package com.authentication.authorization.annotation;

import com.authentication.authorization.validator.UsernameValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Constraint(validatedBy = UsernameValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidUserName {

    String message() default "Username must be valid email or 10-digit mobile number";

    Class <?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
