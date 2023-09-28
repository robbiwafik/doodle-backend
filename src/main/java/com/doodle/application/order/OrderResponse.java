package com.doodle.application.order;

import com.doodle.application.user.CustomerResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderResponse {
    private String id;
    private Date createdAt;
    private CustomerResponse customer;
    private String status;

    public OrderResponse(Order order) {
        id = order.getId();
        createdAt = order.getCreatedAt();
        customer = new CustomerResponse(order.getCustomer());
        status = order.getStatus().name();
    }
}
