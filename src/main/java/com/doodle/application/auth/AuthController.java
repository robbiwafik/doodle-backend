package com.doodle.application.auth;

import com.doodle.application.jwt.JwtService;
import com.doodle.application.user.AdminUserDTO;
import com.doodle.application.user.CustomerUserDTO;
import com.doodle.application.user.User;
import com.doodle.application.user.UserService;
import com.doodle.application.utils.InvalidInputFieldsResponse;
import com.doodle.application.utils.ValidationUtil;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;

    @Autowired
    private AuthService authService;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private ValidationUtil validationUtil;

    @PostMapping("/register/admin")
    public ResponseEntity register(
            @Valid @RequestBody AdminUserDTO adminUserDTO,
            BindingResult validationResult
    ){
        if (validationResult.hasErrors())
            return handleInvalidInputFields(validationResult);

        User adminUser = authService.registerAdmin(adminUserDTO);

        return new ResponseEntity(
                new RegisteredAdminUserResponse(adminUser),
                HttpStatus.CREATED
        );
    }

    @PostMapping("/register/user")
    public ResponseEntity register(
            @Valid @RequestBody CustomerUserDTO customerUserDTO,
            BindingResult validationResult
    ) {
        if (validationResult.hasErrors())
            return handleInvalidInputFields(validationResult);

        User customerUser = authService.registerCustomer(customerUserDTO);

        return new ResponseEntity(
                new RegisteredCustomerUserResponse(customerUser),
                HttpStatus.CREATED
        );
    }

    @PostMapping("/jwt/admin/create")
    public ResponseEntity createAdminToken(
            @Valid @RequestBody AuthenticationDTO authenticationDTO,
            BindingResult validationResult
    ) {
        if (validationResult.hasErrors())
            return handleInvalidInputFields(validationResult);

        try {
            User user = (User) authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            authenticationDTO.getUsername(),
                            authenticationDTO.getPassword()
                    )
            ).getPrincipal();
            if (!userService.isAdminUser(user))
                return new ResponseEntity(
                        new AuthenticationErrorResponse("Invalid credentials."),
                        HttpStatus.BAD_REQUEST
                );
            String jwt = jwtService.generateToken(user);

            return ResponseEntity.ok(new JwtAuthResponse(jwt));
        }
        catch (AuthenticationException exc) {
            return new ResponseEntity(
                    new AuthenticationErrorResponse(exc.getMessage()),
                    HttpStatus.BAD_REQUEST
            );
        }
    }

    @PostMapping("/jwt/customer/create")
    public ResponseEntity createCustomerToken(
            @Valid @RequestBody AuthenticationDTO authenticationDTO,
            BindingResult validationResult
    ) {
        if (validationResult.hasErrors())
            return handleInvalidInputFields(validationResult);

        try {
            User user = (User) authenticationManager.authenticate(
                            new UsernamePasswordAuthenticationToken(
                                    authenticationDTO.getUsername(),
                                    authenticationDTO.getPassword()
                            )
                    )
                    .getPrincipal();

            if (!userService.isCustomerUser(user))
                return new ResponseEntity(
                        new AuthenticationErrorResponse("Invalid credentials"),
                        HttpStatus.BAD_REQUEST
                );

            String jwt = jwtService.generateToken(user);

            return ResponseEntity.ok(new JwtAuthResponse(jwt));
        }
        catch (AuthenticationException exc) {
            return new ResponseEntity(
                    new AuthenticationErrorResponse(exc.getMessage()),
                    HttpStatus.BAD_REQUEST
            );
        }
    }

    private ResponseEntity handleInvalidInputFields(BindingResult validationResult) {
        Map<String, String> errors = validationUtil.mapInputValidationErrors(validationResult);

        return new ResponseEntity(
                new InvalidInputFieldsResponse(errors),
                HttpStatus.BAD_REQUEST
        );
    }
}
