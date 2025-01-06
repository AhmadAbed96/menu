package com.category.crud.Validation.annotation;

import com.category.crud.Validation.CategoryTypeValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.RECORD_COMPONENT,ElementType.TYPE,ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = CategoryTypeValidator.class)
public @interface CategoryType {
    public String message() default "the type should be one of this category {Drinks, Donuts, Ice Cream}";
    public Class<?>[] groups() default {};
    public Class<? extends Payload>[] payload() default {};
}
