package com.doodle.application.orderItem;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/orders/{orderId}/items")
public class OrderItemController {
    @Autowired
    private OrderItemService orderItemService;

    @GetMapping
    public ResponseEntity getAllOrderItems(@PathVariable String orderId) {
        List<OrderItem> orderItems = orderItemService.getAssociatedOrderItems(orderId);

        List<OrderItemResponse> response = orderItems
                .stream()
                .map(orderItem -> new OrderItemResponse(orderItem))
                .collect(Collectors.toList());

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity getOrderItem(
            @PathVariable String orderId,
            @PathVariable int id
    ) {
        OrderItem orderItem = orderItemService.getOrderItem(orderId, id);

        return ResponseEntity.ok(new OrderItemResponse(orderItem));
    }

    @PostMapping
    public ResponseEntity createOrderItem(
            @PathVariable String orderId,
            @Valid @RequestBody OrderItemDTO orderItemDTO
    ) {
        OrderItem orderItem = orderItemService.createOrderItem(orderId, orderItemDTO);

        return new ResponseEntity(
                new OrderItemResponse(orderItem),
                HttpStatus.CREATED
        );
    }
}
