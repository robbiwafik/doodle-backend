package com.doodle.application.auth;

import com.doodle.application.user.User;
import org.springframework.security.core.context.SecurityContextHolder;


public class AuthenticationUtil {
    public static User getCurrentAuthenticatedUser() {
        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
