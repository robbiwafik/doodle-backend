package com.doodle.application.product;

import com.doodle.application.category.CategoryResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductResponse {
    private String name;
    private Double price;
    private String description;
    private Integer numberInStock;
    private Date createdDate;
    private Date lastModifiedDate;
    private CategoryResponse category;

    public ProductResponse(Product product) {
        name = product.getName();
        price = product.getPrice();
        description = product.getDescription();
        numberInStock = product.getNumberInStock();
        createdDate = product.getCreatedDate();
        lastModifiedDate = product.getLastModifiedDate();
        category = new CategoryResponse(product.getCategory());
    }
}
