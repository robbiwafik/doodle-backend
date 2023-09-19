package com.doodle.application.user;


import com.doodle.application.validation.annotations.UniqueUsername;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdminUserDTO {
    @NotBlank(message = "First name is required.")
    @NotNull
    private String firstName;

    @NotBlank(message = "Last name is required.")
    @NotNull
    private String lastName;

    @NotBlank(message = "Username is required")
    @NotNull
    @Length(min = 8, max = 30)
    @UniqueUsername
    private String username;

    @NotBlank(message = "Password is required")
    @NotNull
    @Length(min = 8, max = 30)
    private String password;
}
