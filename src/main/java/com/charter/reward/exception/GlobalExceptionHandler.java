package com.charter.reward.exception;

import jakarta.validation.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

/**
 * Global exception handler for the Rewards API.
 * <p>
 * This class centralizes exception handling across all REST controllers
 * and returns a consistent error response to clients.
 * </p>
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Logger for recording application exceptions.
     */
    private static final Logger logger =
            LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * Handles CustomerNotFoundException.
     *
     * @param ex the customer not found exception
     * @return ResponseEntity containing the error response
     */
    @ExceptionHandler(CustomerNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleCustomerNotFound(
            CustomerNotFoundException ex) {

        logger.error("Customer not found: {}", ex.getMessage());

        ErrorResponse error = new ErrorResponse(
                LocalDateTime.now(),
                HttpStatus.NOT_FOUND.value(),
                "Customer Not Found",
                ex.getMessage());

        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    /**
     * Handles InvalidRequestException.
     *
     * @param ex the invalid request exception
     * @return ResponseEntity containing the error response
     */
    @ExceptionHandler(InvalidRequestException.class)
    public ResponseEntity<ErrorResponse> handleInvalidRequest(
            InvalidRequestException ex) {

        logger.error("Invalid request: {}", ex.getMessage());

        ErrorResponse error = new ErrorResponse(
                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST.value(),
                "Bad Request",
                ex.getMessage());

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    /**
     * Handles validation errors.
     *
     * @param ex the constraint violation exception
     * @return ResponseEntity containing the validation error response
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorResponse> handleConstraintViolation(
            ConstraintViolationException ex) {

        logger.error("Validation failed: {}", ex.getMessage());

        ErrorResponse error = new ErrorResponse(
                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST.value(),
                "Validation Failed",
                ex.getMessage());

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    /**
     * Handles all unexpected exceptions.
     *
     * @param ex the unexpected exception
     * @return ResponseEntity containing the internal server error response
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception ex) {

        logger.error("Unexpected exception occurred", ex);

        ErrorResponse error = new ErrorResponse(
                LocalDateTime.now(),
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "Internal Server Error",
                ex.getMessage());

        return new ResponseEntity<>(error,
                HttpStatus.INTERNAL_SERVER_ERROR);
    }
}