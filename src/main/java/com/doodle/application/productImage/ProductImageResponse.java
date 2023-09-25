package com.doodle.application.productImage;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductImageResponse {
    private Integer id;
    private String name;
    private String type;
    private Integer productId;
    private String endpoint;

    public ProductImageResponse(ProductImage productImage) {
        id = productImage.getId();
        name = productImage.getName();
        type = productImage.getType();
        productId = productImage.getProduct().getId();
        endpoint = "/uploads/product/images/" + name;
    }
}
