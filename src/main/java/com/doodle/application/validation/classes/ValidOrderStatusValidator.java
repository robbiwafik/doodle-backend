package com.doodle.application.validation.classes;

import com.doodle.application.validation.annotations.ValidOrderStatus;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;


public class ValidOrderStatusValidator implements ConstraintValidator<ValidOrderStatus, String> {
    @Override
    public boolean isValid(String status, ConstraintValidatorContext constraintValidatorContext) {
        status = status.toUpperCase();
        if (status.equals("PENDING") || status.equals("SUCCEED") || status.equals("FAILED"))
            return true;
        return false;
    }
}
