package com.doodle.application.cart;

import com.doodle.application.exception.BadRequestException;
import com.doodle.application.user.User;
import com.doodle.application.auth.AuthenticationUtil;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class CartService {
    @Autowired
    private CartRepository cartRepository;

    public List<Cart> getAllCarts() {
        return cartRepository.findAll();
    }

    public Cart createCart() {
        User customer = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Optional<Cart> optionalCart = cartRepository.findByCustomer(customer);
        if (optionalCart.isPresent())
            throw new BadRequestException("A customer can't have multiple carts.");

        Cart cart = Cart.builder()
                .customer(customer)
                .build();

        return cartRepository.save(cart);
    }

    public Cart getCustomerAssociatedCart() {
        User customer = AuthenticationUtil.getCurrentAuthenticatedUser();
        return cartRepository.findByCustomer(customer)
                .orElseThrow(() -> new EntityNotFoundException("The current customer hasn't created a cart."));
    }
}
