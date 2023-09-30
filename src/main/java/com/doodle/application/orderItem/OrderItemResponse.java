package com.doodle.application.orderItem;

import com.doodle.application.product.SimpleProductResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemResponse {
    private Integer id;
    private Integer quantity;
    private Double totalPrice;
    private SimpleProductResponse product;
    private String orderId;

    public OrderItemResponse(OrderItem orderItem) {
        id = orderItem.getId();
        quantity = orderItem.getQuantity();
        product = new SimpleProductResponse(orderItem.getProduct());
        orderId = orderItem.getOrder().getId();
        totalPrice = orderItem.getProduct().getPrice() * quantity;
    }
}
