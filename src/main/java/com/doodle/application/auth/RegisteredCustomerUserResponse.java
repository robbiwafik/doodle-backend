package com.doodle.application.auth;

import com.doodle.application.role.Role;
import com.doodle.application.user.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisteredCustomerUserResponse {

    public RegisteredCustomerUserResponse(User customerUser) {
        id = customerUser.getId();
        firstName = customerUser.getFirstName();
        lastName = customerUser.getLastName();
        address = customerUser.getAddress();
        role = customerUser.getRole();
        username = customerUser.getUsername();
    }

    private Integer id;
    private String firstName;
    private String lastName;
    private String address;
    private Role role;
    private String username;
}
