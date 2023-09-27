package com.doodle.application.cart;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartResponse {
    private String id;
    private Integer customerId;

    public CartResponse(Cart cart) {
        id = cart.getId();
        customerId = cart.getCustomer().getId();
    }
}
