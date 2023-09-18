package com.doodle.application.utils;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.HashMap;
import java.util.Map;

@Component
public class ValidationUtil {

    public Map<String, String> mapInputValidationErrors(BindingResult validationResult) {
        Map<String, String> errors = new HashMap();
        for (FieldError fieldError : validationResult.getFieldErrors())
            errors.put(fieldError.getField(), fieldError.getDefaultMessage());

        return errors;
    }
}
