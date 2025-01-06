package com.category.crud.Validation;

import com.category.crud.Repository.SectionRepository;
import com.category.crud.Validation.annotation.ValidSection;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

public class ValidSectionValidator implements ConstraintValidator<ValidSection, String> {

    @Autowired
    SectionRepository sectionRepo;

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return sectionRepo.existsById(s);
    }
}
