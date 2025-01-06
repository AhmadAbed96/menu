package com.category.crud.Exception;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class NotFound extends RuntimeException{
    public NotFound(String message){
        super(message);
    }

}
