package com.doodle.application.cart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/cart")
public class CartController {
    @Autowired
    private CartService cartService;

    @GetMapping
    public ResponseEntity getCurrentCustomerCart() {
        Cart cart = cartService.getCustomerAssociatedCart();

        return ResponseEntity.ok(new CartResponse(cart));
    }

    @PostMapping
    public ResponseEntity createCart() {
        Cart cart = cartService.createCart();

        return new ResponseEntity(
                new CartResponse(cart),
                HttpStatus.CREATED
        );
    }
}
