package com.doodle.application.validation.annotations;

import com.doodle.application.validation.classes.ValidOrderStatusValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ValidOrderStatusValidator.class)
public @interface ValidOrderStatus {
    String message() default "Status can only either be 'PENDING', 'SUCCEED', or 'FAILED'";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
