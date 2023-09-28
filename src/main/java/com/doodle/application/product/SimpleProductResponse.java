package com.doodle.application.product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class SimpleProductResponse {
    private Integer id;
    private String name;
    private Double price;

    public SimpleProductResponse(Product product) {
        id = product.getId();
        name = product.getName();
        price = product.getPrice();
    }
}
