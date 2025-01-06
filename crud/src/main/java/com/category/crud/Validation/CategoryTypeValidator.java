package com.category.crud.Validation;

import com.category.crud.Validation.annotation.CategoryType;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Arrays;
import java.util.List;

public class CategoryTypeValidator implements ConstraintValidator<CategoryType, String> {
    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        List<String> CategoryTypes = Arrays.asList("Drinks","Ice Cream", "Donuts");
        return CategoryTypes.contains(value);
    }
}
