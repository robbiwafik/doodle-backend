package com.doodle.application.auth;

import com.doodle.application.jwt.JwtService;
import com.doodle.application.role.Role;
import com.doodle.application.user.AdminUserDTO;
import com.doodle.application.user.CustomerUserDTO;
import com.doodle.application.user.User;
import com.doodle.application.user.UserService;
import com.doodle.application.utils.ValidationUtil;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public ResponseEntity register(@Valid @RequestBody AdminUserDTO adminUserDTO) {
        User adminUser = authService.registerAdmin(adminUserDTO);

        return new ResponseEntity(
                new RegisteredAdminUserResponse(adminUser),
                HttpStatus.CREATED
        );
    }

    @PostMapping("/register/customer")
    public ResponseEntity register(@Valid @RequestBody CustomerUserDTO customerUserDTO) {
        User customerUser = authService.registerCustomer(customerUserDTO);

        return new ResponseEntity(
                new RegisteredCustomerUserResponse(customerUser),
                HttpStatus.CREATED
        );
    }

    @PostMapping("/jwt/admin/create")
    public ResponseEntity createAdminToken(@Valid @RequestBody AuthenticationDTO authenticationDTO) {
        return createTokenFor(
                authenticationDTO.getUsername(),
                authenticationDTO.getPassword(),
                Role.ADMIN
        );
    }

    @PostMapping("/jwt/customer/create")
    public ResponseEntity createCustomerToken(@Valid @RequestBody AuthenticationDTO authenticationDTO) {
        return createTokenFor(
                authenticationDTO.getUsername(),
                authenticationDTO.getPassword(),
                Role.CUSTOMER
        );
    }

    private ResponseEntity createTokenFor(String username, String password, Role role) {
        User user = authService.authenticate(username, password);
        if (!user.getRole().equals(role))
            return new ResponseEntity(
                    new AuthenticationErrorResponse("Invalid credentials."),
                    HttpStatus.UNAUTHORIZED
            );

        String jwt = jwtService.generateToken(user);

        return ResponseEntity.ok(new JwtAuthResponse(jwt));
    }
}
