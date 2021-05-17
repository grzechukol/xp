package com.transactions;

public class IncorrectDataException extends Exception {
    public IncorrectDataException(String errorMessage) {
        super(errorMessage);
    }
}
