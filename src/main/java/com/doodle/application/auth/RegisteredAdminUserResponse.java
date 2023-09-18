package com.doodle.application.auth;


import com.doodle.application.user.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisteredAdminUserResponse {
    public RegisteredAdminUserResponse(User user) {
        id = user.getId();
        firstName = user.getFirstName();
        lastName = user.getLastName();
        username = user.getUsername();
    }

    private Integer id;
    private String firstName;
    private String lastName;
    private String username;
}
