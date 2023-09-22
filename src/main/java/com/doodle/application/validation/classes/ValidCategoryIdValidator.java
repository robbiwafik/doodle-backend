package com.doodle.application.validation.classes;

import com.doodle.application.category.CategoryService;
import com.doodle.application.validation.annotations.ValidCategoryId;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;


public class ValidCategoryIdValidator implements ConstraintValidator<ValidCategoryId, Integer> {
    @Autowired
    CategoryService categoryService;

    @Override
    public boolean isValid(Integer id, ConstraintValidatorContext constraintValidatorContext) {
        try {
            categoryService.getCategory(id);
        }
        catch (EntityNotFoundException exc) {
            return false;
        }

        return true;
    }
}
