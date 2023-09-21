package com.doodle.application.category;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryResponse {
    private Integer id;
    private String name;

    public CategoryResponse(Category category) {
        id = category.getId();
        name = category.getName();
    }
}
