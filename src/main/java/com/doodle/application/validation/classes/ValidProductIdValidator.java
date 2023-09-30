package com.doodle.application.validation.classes;

import com.doodle.application.product.ProductService;
import com.doodle.application.validation.annotations.ValidProductId;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;


public class ValidProductIdValidator implements ConstraintValidator<ValidProductId, Integer> {
    @Autowired
    private ProductService productService;

    @Override
    public boolean isValid(Integer id, ConstraintValidatorContext constraintValidatorContext) {
        if (id == null) return false;

        try {
            productService.getProduct(id);
            return true;
        }
        catch (EntityNotFoundException exception) {
            return false;
        }
    }
}
