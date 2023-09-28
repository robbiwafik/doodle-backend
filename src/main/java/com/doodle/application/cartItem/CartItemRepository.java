package com.doodle.application.cartItem;

import com.doodle.application.cart.Cart;
import com.doodle.application.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Integer> {
    List<CartItem> findAllByCart(Cart cart);

    Optional<CartItem> findByIdAndCart(int id, Cart cart);

    Optional<CartItem> findByProduct(Product product);
}
