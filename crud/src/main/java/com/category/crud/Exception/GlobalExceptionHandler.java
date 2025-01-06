package com.category.crud.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDate;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(NotFound.class)
    public ResponseEntity<?> handleCategoryNotFoundException(NotFound e) {
        ErrorResponse categoryNotFound = new ErrorResponse(e.getMessage(),"not found",LocalDate.now()) {

        };
        HttpStatusCode httpStatusCode = HttpStatus.NOT_FOUND;
        return new ResponseEntity<>(categoryNotFound, httpStatusCode);
    }

    @ExceptionHandler(CategoryNotUnique.class)
    public ResponseEntity<?> handleCategoryNotUniqueException(CategoryNotUnique e) {
        ErrorResponse categoryNotUnique = new ErrorResponse(e.getMessage(),"Category with that name already exist",LocalDate.now()) {
        };
        HttpStatusCode httpStatusCode = HttpStatus.CONFLICT;
        return new ResponseEntity<>(categoryNotUnique, httpStatusCode);
    }

    @ExceptionHandler(SectionNotFound.class)
    public ResponseEntity<?> handleSectionNotFoundException(SectionNotFound e) {
        ErrorResponse sectionNotFound = new ErrorResponse(e.getMessage(),"Section not found",LocalDate.now()) {

        };
        HttpStatusCode httpStatusCode = HttpStatus.NOT_FOUND;
        return new ResponseEntity<>(sectionNotFound, httpStatusCode);
    }

    @ExceptionHandler(ItemNotFound.class)
    public ResponseEntity<?> handleItemNotFoundException(ItemNotFound e) {
        ErrorResponse sectionNotFound = new ErrorResponse(e.getMessage(),"Item not found",LocalDate.now()) {

        };
        HttpStatusCode httpStatusCode = HttpStatus.NOT_FOUND;
        return new ResponseEntity<>(sectionNotFound, httpStatusCode);
    }
}
