package com.vikram.macquarie.bankdemo.repository;

/**
 * Extending CheckedException so that the layer above can retry
 * if needed
 */
public class DatabaseException extends Exception {
    public DatabaseException(String message, Exception e) {
        super(message, e);
    }
}
