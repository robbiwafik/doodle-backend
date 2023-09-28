package com.doodle.application.cartItem;

import com.doodle.application.product.SimpleProductResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartItemResponse {
    private Integer id;
    private Integer quantity;
    private SimpleProductResponse product;
    private String cartId;
    private Double totalPrice;

    public CartItemResponse(CartItem cartItem) {
        id = cartItem.getId();
        quantity = cartItem.getQuantity();
        product = new SimpleProductResponse(cartItem.getProduct());
        cartId = cartItem.getCart().getId();
        totalPrice = countTotalPrice(cartItem);
    }

    private Double countTotalPrice(CartItem cartItem) {
        Double productPrice = cartItem.getProduct().getPrice();

        return productPrice * cartItem.getQuantity();
    }
}
