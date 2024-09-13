package com.vikram.macquarie.bankdemo.service;

public class DataAccessException extends Exception {
    public DataAccessException(String message, Exception e) {
        super(message, e);
    }
}
