package com.category.crud.Exception;

public class CategoryNotUnique extends RuntimeException {
    public CategoryNotUnique(String message) {
        super(message);
    }
}
