package com.doodle.application.orderItem;

import com.doodle.application.validation.annotations.ValidProductId;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemDTO {
    @NotNull(message = "Quantity field is required.")
    @Min(1)
    private Integer quantity;

    @NotNull(message = "Product id field is required.")
    @ValidProductId
    private Integer productId;
}
