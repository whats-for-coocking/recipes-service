package com.whatsforcoocking.recipeservice.exception;

public class NotAllowedException extends RuntimeException{

    public NotAllowedException(String message){
        super(message);
    }
}
