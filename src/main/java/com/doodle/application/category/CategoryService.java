package com.doodle.application.category;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    public Category getCategory(int id) {
        return categoryRepository
                .findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Couldn't find a category with id " + id + "."));
    }

    public Category createCategory(CategoryDTO categoryDTO) {
        Category category = Category
                .builder()
                .name(categoryDTO.getName())
                .build();

        return categoryRepository.save(category);
    }

    public Category updateCategory(int id, CategoryDTO categoryDTO) {
        Category category = getCategory(id);
        category.setName(categoryDTO.getName());

        return categoryRepository.save(category);
    }

    public void deleteCategory(int id) {
        Category category = getCategory(id);

        categoryRepository.delete(category);
    }
}
