package com.whatsforcoocking.recipeservice.exception;

public class PayloadSerializationException extends RuntimeException{

    public PayloadSerializationException(String message, Throwable cause) {
        super(message, cause);
    }
}
