package com.doodle.application.auth;

import com.doodle.application.role.Role;
import com.doodle.application.user.AdminUserDTO;
import com.doodle.application.user.CustomerUserDTO;
import com.doodle.application.user.User;
import com.doodle.application.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User registerAdmin(AdminUserDTO adminUserDTO) {
        User adminUser = User.builder()
                .firstName(adminUserDTO.getFirstName())
                .lastName(adminUserDTO.getLastName())
                .role(Role.ADMIN)
                .username(adminUserDTO.getUsername())
                .password(passwordEncoder.encode(adminUserDTO.getPassword()))
                .build();

        return userService.saveUser(adminUser);
    }

    public User registerCustomer(CustomerUserDTO customerUserDTO) {
        User customerUser = User.builder()
                .firstName(customerUserDTO.getFirstName())
                .lastName(customerUserDTO.getLastName())
                .address(customerUserDTO.getAddress())
                .role(Role.CUSTOMER)
                .username(customerUserDTO.getUsername())
                .password(passwordEncoder.encode(customerUserDTO.getPassword()))
                .build();

        return userService.saveUser(customerUser);
    }
}
