package com.category.crud.Validation;

import com.category.crud.Repository.CategoryRepository;
import com.category.crud.Validation.annotation.UniqueCategoryValue;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

public class UniqueValueValidator implements ConstraintValidator<UniqueCategoryValue, String> {

    @Autowired
    CategoryRepository categoryRepo;


    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if (categoryRepo.findCategoryByName(s).size() == 0 ){
            return true;
        }
        return false;
    }


}
