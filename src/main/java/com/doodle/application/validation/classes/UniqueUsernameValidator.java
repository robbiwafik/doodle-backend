package com.doodle.application.validation.classes;

import com.doodle.application.user.UserService;
import com.doodle.application.validation.annotations.UniqueUsername;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class UniqueUsernameValidator implements ConstraintValidator<UniqueUsername, String> {
    @Autowired
    private UserService userService;

    @Override
    public boolean isValid(
            String username,
            ConstraintValidatorContext constraintValidatorContext
    ) {
        try {
            userService.loadUserByUsername(username);
        }
        catch (UsernameNotFoundException exc) {
            return true;
        }

        return false;
    }
}
