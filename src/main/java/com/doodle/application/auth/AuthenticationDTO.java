package com.doodle.application.auth;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthenticationDTO {
    @NotBlank(message = "Username is required.")
    @NotNull(message = "Username can't be empty.")
    private String username;

    @NotBlank(message = "Password is required.")
    @NotNull(message = "Password can't be empty")
    private String password;
}
