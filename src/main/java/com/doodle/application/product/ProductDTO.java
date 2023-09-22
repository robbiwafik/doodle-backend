package com.doodle.application.product;

import com.doodle.application.validation.annotations.ValidCategoryId;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {
    @NotNull(message = "Name field is required.")
    @NotBlank(message = "Name field can't be empty.")
    private String name;

    @NotNull(message = "Price field is required.")
    @DecimalMin(value = "0.00", message = "Price must be at least {value}.")
    private Double price;

    private String description;

    @NotNull(message = "Stock field is required.")
    @Min(value = 0, message = "Stock must be at least {value}.")
    private Integer numberInStock;

    @NotNull(message = "Category field is required.")
    @ValidCategoryId
    private Integer categoryId;
}
