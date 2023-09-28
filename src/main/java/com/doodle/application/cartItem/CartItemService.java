package com.doodle.application.cartItem;

import com.doodle.application.cart.Cart;
import com.doodle.application.cart.CartService;
import com.doodle.application.product.Product;
import com.doodle.application.product.ProductService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class CartItemService {
    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private CartService cartService;

    @Autowired
    private ProductService productService;

    public List<CartItem> getAssociatedCartItems(String cartId) {
        Cart cart = cartService.getCart(cartId);
        return cartItemRepository.findAllByCart(cart);
    }

    public CartItem getCartItem(String cartId, int id) {
        Cart cart = cartService.getCart(cartId);

        return cartItemRepository.findByIdAndCart(id, cart)
                .orElseThrow(
                        () -> new EntityNotFoundException(
                                "Couldn't find an item with id " + id + " associated with cart with id " + cartId
                        )
                );
    }

    public CartItem createCartItem(String cartId, CartItemDTO cartItemDTO) {
        Product product = productService.getProduct(cartItemDTO.getProductId());
        Cart cart = cartService.getCart(cartId);
        CartItem cartItem = CartItem.builder()
                .quantity(cartItemDTO.getQuantity())
                .cart(cart)
                .product(product)
                .build();

        return cartItemRepository.save(cartItem);
    }

    public CartItem updateCartItemQuantity(String cartId, int id, int quantity) {
        CartItem cartItem = getCartItem(cartId, id);
        cartItem.setQuantity(quantity);

        return cartItemRepository.save(cartItem);
    }

    public CartItem createOrUpdateQuantityCartItem(String cartId, CartItemDTO cartItemDTO) {
        Product product = productService.getProduct(cartItemDTO.getProductId());
        Optional<CartItem> optionalCartItem = cartItemRepository.findByProduct(product);
        if (optionalCartItem.isEmpty())
            return createCartItem(cartId, cartItemDTO);

        CartItem cartItem = optionalCartItem.get();
        int quantity = cartItem.getQuantity() + cartItemDTO.getQuantity();
        return updateCartItemQuantity(cartId, cartItem.getId(), quantity);
    }

    public void deleteCartItem(String cartId, int id) {
        CartItem cartItem = getCartItem(cartId, id);
        cartItemRepository.delete(cartItem);
    }
}
