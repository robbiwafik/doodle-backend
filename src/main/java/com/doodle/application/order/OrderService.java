package com.doodle.application.order;

import com.doodle.application.auth.AuthenticationUtil;
import com.doodle.application.user.User;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Order getOrder(String id) {
        return orderRepository
                .findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Order with given id " + id + " does not exist."));
    }

    public List<Order> getCurrentCustomerOrders() {
        User customer = AuthenticationUtil.getCurrentAuthenticatedUser();

        return orderRepository.findAllByCustomer(customer);
    }

    public Order createOrder() {
        User customer = AuthenticationUtil.getCurrentAuthenticatedUser();
        Order order = Order.builder()
                .customer(customer)
                .status(OrderStatus.PENDING)
                .build();

        return orderRepository.save(order);
    }

    public Order updateOrderStatus(String id, String status) {
        OrderStatus orderStatus = OrderStatus.valueOf(status);
        Order order = getOrder(id);
        order.setStatus(orderStatus);

        return orderRepository.save(order);
    }
}
