package com.doodle.application.cartItem;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartItemPartialDTO {
    @NotNull(message = "Quantity field is required.")
    @Min(1)
    private Integer quantity;
}
