package com.doodle.application.category;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDTO {
    @NotNull(message = "Name is required.")
    @NotBlank(message = "Name is required.")
    private String name;
}
