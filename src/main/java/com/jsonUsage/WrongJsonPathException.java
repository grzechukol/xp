package com.jsonUsage;

public class WrongJsonPathException extends Exception {
    public WrongJsonPathException(String errorMessage) {
        super(errorMessage);
    }
}
