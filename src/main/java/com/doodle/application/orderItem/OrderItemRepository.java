package com.doodle.application.orderItem;

import com.doodle.application.order.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Integer> {
    List<OrderItem> findAllByOrder(Order order);

    Optional<OrderItem> findByIdAndOrder(int id, Order order);
}
