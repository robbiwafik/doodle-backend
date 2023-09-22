package com.doodle.application.validation.annotations;

import com.doodle.application.validation.classes.ValidCategoryIdValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ValidCategoryIdValidator.class)
public @interface ValidCategoryId {
    String message() default "Category with given id does not exist.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
