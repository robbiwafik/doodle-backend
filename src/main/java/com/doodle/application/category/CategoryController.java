package com.doodle.application.category;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/categories")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping()
    public ResponseEntity getAllCategories() {
        List<Category> allCategories = categoryService.getAllCategories();

        return ResponseEntity.ok(allCategories);
    }

    @GetMapping("/{id}")
    public ResponseEntity getCategory(@PathVariable int id) {
        Category category = categoryService.getCategory(id);

        return ResponseEntity.ok(new CategoryResponse(category));
    }

    @PostMapping()
    public ResponseEntity createCategory(@Valid @RequestBody CategoryDTO categoryDTO) {
        Category createdCategory = categoryService.createCategory(categoryDTO);

        return new ResponseEntity(
                new CategoryResponse(createdCategory),
                HttpStatus.CREATED
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity updateCategory(
            @PathVariable int id,
            @Valid @RequestBody CategoryDTO categoryDTO
    ) {
        Category updatedCategory = categoryService.updateCategory(id, categoryDTO);

        return ResponseEntity.ok(new CategoryResponse(updatedCategory));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteCategory(@PathVariable int id) {
        categoryService.deleteCategory(id);

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
