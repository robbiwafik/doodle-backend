package com.doodle.application.cartItem;

import com.doodle.application.validation.annotations.ValidProductId;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartItemDTO {
    @NotNull(message = "Quantity field can't be empty.")
    @Min(1)
    private Integer quantity;

    @NotNull(message = "Product id field can't be empty.")
    @ValidProductId
    private Integer productId;

}
