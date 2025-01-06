package com.category.crud.Validation.annotation;

import com.category.crud.Validation.UniqueValueValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD, ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {UniqueValueValidator.class})
public @interface UniqueCategoryValue {
    public String message() default "Invalid, already exist";
    public Class<?>[] groups() default {};
    public Class<? extends Payload>[] payload() default {};
}
