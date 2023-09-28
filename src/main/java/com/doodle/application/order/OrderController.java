package com.doodle.application.order;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.naming.AuthenticationException;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @GetMapping
    public ResponseEntity getAllOrders() {
        List<Order> orders = orderService.getAllOrders();

        return ResponseEntity.ok(mapOrderList(orders));
    }

    @GetMapping("/me")
    public ResponseEntity getCurrentCustomerOrders() {
        List<Order> orders = orderService.getCurrentCustomerOrders();

        return ResponseEntity.ok(mapOrderList(orders));
    }

    @GetMapping("/{id}")
    public ResponseEntity getOrder(@PathVariable String id) {
        Order order = orderService.getOrder(id);

        return ResponseEntity.ok(new OrderResponse(order));
    }

    @PostMapping
    public ResponseEntity createOrder() throws AuthenticationException {
        Order order = orderService.createOrder();

        return new ResponseEntity(new OrderResponse(order), HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity updateOrderStatus(
            @PathVariable String id,
            @Valid @RequestBody OrderPartialDTO orderPartialDTO
    ) {
        Order order = orderService.updateOrderStatus(id, orderPartialDTO.getStatus());

        return ResponseEntity.ok(new OrderResponse(order));
    }

    private List<OrderResponse> mapOrderList(List<Order> orders) {
        return orders
                .stream()
                .map(order -> new OrderResponse(order))
                .collect(Collectors.toList());
    }
}
