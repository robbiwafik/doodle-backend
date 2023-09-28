package com.doodle.application.validation.annotations;

import com.doodle.application.validation.classes.ValidProductIdValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ValidProductIdValidator.class)
public @interface ValidProductId {
    String message() default "Product with given id does not exist.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
