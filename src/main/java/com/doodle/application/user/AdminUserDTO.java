package com.doodle.application.user;


import com.doodle.application.validation.annotations.UniqueUsername;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdminUserDTO {
    @NotBlank(message = "First name is required.")
    private String firstName;

    @NotBlank(message = "Last name is required.")
    private String lastName;

    @NotBlank(message = "Username is required")
    @Length(min = 8, max = 30)
    @UniqueUsername
    private String username;

    @NotBlank(message = "Password is required")
    @Length(min = 8, max = 30)
    private String password;
}
