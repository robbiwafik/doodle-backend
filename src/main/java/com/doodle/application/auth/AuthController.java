package com.doodle.application.auth;

import com.doodle.application.user.AdminUserDTO;
import com.doodle.application.user.User;
import com.doodle.application.utils.InvalidInputFieldsResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    @PostMapping("/register/admin")
    public ResponseEntity register(
            @Valid @RequestBody AdminUserDTO adminUserDTO,
            BindingResult result
    ){
        if (result.hasErrors()) {
            Map<String, String> errors = new HashMap();
            for (FieldError fieldError : result.getFieldErrors())
                errors.put(fieldError.getField(), fieldError.getDefaultMessage());

            return new ResponseEntity(
                    new InvalidInputFieldsResponse(errors),
                    HttpStatus.BAD_REQUEST
            );
        }
        User adminUser = authService.registerAdmin(adminUserDTO);

        return new ResponseEntity(
                new RegisteredAdminUserResponse(adminUser),
                HttpStatus.CREATED
        );
    }
}
