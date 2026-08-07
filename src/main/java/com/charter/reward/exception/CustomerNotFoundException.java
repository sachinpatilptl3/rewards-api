package com.charter.reward.exception;

/**
 * Exception thrown when the requested customer
 * does not have any transactions or does not exist.
 */
public class CustomerNotFoundException extends RuntimeException {

    /**
     * Creates a new CustomerNotFoundException.
     *
     * @param message exception message
     */
    public CustomerNotFoundException(String message) {
        super(message);
    }
}