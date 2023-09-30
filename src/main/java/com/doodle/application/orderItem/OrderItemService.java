package com.doodle.application.orderItem;

import com.doodle.application.order.Order;
import com.doodle.application.order.OrderService;
import com.doodle.application.product.Product;
import com.doodle.application.product.ProductService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class OrderItemService {
    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private OrderService orderService;

    @Autowired
    private ProductService productService;

    public List<OrderItem> getAssociatedOrderItems(String orderId) {
        Order order = orderService.getOrder(orderId);

        return orderItemRepository.findAllByOrder(order);
    }

    public OrderItem getOrderItem(String orderId, int id) {
        Order order = orderService.getOrder(orderId);

        return orderItemRepository
                .findByIdAndOrder(id, order)
                .orElseThrow(
                        () -> new EntityNotFoundException(
                                "Couldn't find an item with id " + id + " associated with order with id " + orderId
                        )
                );
    }

    public OrderItem createOrderItem(String orderId, OrderItemDTO orderItemDTO) {
        Order order = orderService.getOrder(orderId);
        Product product = productService.getProduct(orderItemDTO.getProductId());
        OrderItem orderItem = OrderItem.builder()
                .quantity(orderItemDTO.getQuantity())
                .order(order)
                .product(product)
                .build();

        return orderItemRepository.save(orderItem);
    }
}
