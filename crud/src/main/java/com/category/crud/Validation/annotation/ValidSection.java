package com.category.crud.Validation.annotation;


import com.category.crud.Validation.ValidSectionValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {ValidSectionValidator.class})
public @interface ValidSection {
    public String message() default "Invalid, the section not exist";
    public Class<?>[] groups() default {};
    public Class<? extends Payload>[] payload() default {};
}


