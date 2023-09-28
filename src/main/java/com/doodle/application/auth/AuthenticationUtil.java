package com.doodle.application.auth;

import com.doodle.application.user.User;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;


public class AuthenticationUtil {
    public static User getCurrentAuthenticatedUser() throws AuthenticationException {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal == "anonymousUser")
            throw new AuthenticationCredentialsNotFoundException("Missing authorization token.");

        return (User) principal;
    }
}
