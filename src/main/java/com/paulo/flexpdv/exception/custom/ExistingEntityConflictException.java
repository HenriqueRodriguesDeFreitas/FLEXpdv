package com.paulo.flexpdv.exception.custom;

public class ExistingEntityConflictException extends RuntimeException {
    public ExistingEntityConflictException(String message) {
        super(message);
    }
}
