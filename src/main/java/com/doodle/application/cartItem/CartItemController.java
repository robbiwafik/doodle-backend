package com.doodle.application.cartItem;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/carts/{cartId}/items")
public class CartItemController {
    @Autowired
    private CartItemService cartItemService;

    @GetMapping
    public ResponseEntity getAssociatedCartItems(@PathVariable String cartId) {
        List<CartItem> cartItems = cartItemService.getAssociatedCartItems(cartId);

        List<CartItemResponse> response = cartItems
                .stream()
                .map(cartItem -> new CartItemResponse(cartItem))
                .collect(Collectors.toList());

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity getCartItem(
            @PathVariable String cartId,
            @PathVariable int id
    ) {
        CartItem cartItem = cartItemService.getCartItem(cartId, id);

        return ResponseEntity.ok(new CartItemResponse(cartItem));
    }

    @PostMapping
    public ResponseEntity createCartItem(
            @PathVariable String cartId,
            @Valid @RequestBody CartItemDTO cartItemDTO
    ) {
        CartItem createdCart = cartItemService.createOrUpdateQuantityCartItem(cartId, cartItemDTO);

        return new ResponseEntity(
                new CartItemResponse(createdCart),
                HttpStatus.CREATED
        );
    }

    @PatchMapping("/{id}")
    public ResponseEntity updateCartItemQuantity(
            @PathVariable String cartId,
            @PathVariable int id,
            @Valid @RequestBody CartItemPartialDTO cartItemPartialDTO ) {
        CartItem cartItem = cartItemService.updateCartItemQuantity(cartId, id, cartItemPartialDTO.getQuantity());

        return ResponseEntity.ok(new CartItemResponse(cartItem));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteCartItem(
            @PathVariable String cartId,
            @PathVariable int id
    ) {
        cartItemService.deleteCartItem(cartId, id);

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

}
