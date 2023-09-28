package com.doodle.application.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerResponse {
    private Integer id;
    private String firstName;
    private String lastName;
    private String username;
    private String address;
    private String email;

    public CustomerResponse(User customer) {
        id = customer.getId();
        firstName = customer.getFirstName();
        lastName = customer.getLastName();
        username = customer.getUsername();
        address = customer.getAddress();
        email = customer.getEmail();
    }
}
