package com.charter.reward.exception;

import java.time.LocalDateTime;

/**
 * Represents a standard error response returned by the API.
 * <p>
 * This class is used by the global exception handler to provide
 * consistent error details for client requests.
 * </p>
 */
public class ErrorResponse {

    /**
     * Timestamp when the error occurred.
     */
    private LocalDateTime timestamp;

    /**
     * HTTP status code.
     */
    private int status;

    /**
     * Short description of the error.
     */
    private String error;

    /**
     * Detailed error message.
     */
    private String message;

    /**
     * Default constructor.
     */
    public ErrorResponse() {
    }

    /**
     * Creates an ErrorResponse with all fields.
     *
     * @param timestamp the time when the error occurred
     * @param status HTTP status code
     * @param error short error description
     * @param message detailed error message
     */
    public ErrorResponse(LocalDateTime timestamp,
                         int status,
                         String error,
                         String message) {
        this.timestamp = timestamp;
        this.status = status;
        this.error = error;
        this.message = message;
    }

    /**
     * Returns the timestamp of the error.
     *
     * @return error timestamp
     */
    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    /**
     * Sets the timestamp of the error.
     *
     * @param timestamp error timestamp
     */
    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    /**
     * Returns the HTTP status code.
     *
     * @return HTTP status code
     */
    public int getStatus() {
        return status;
    }

    /**
     * Sets the HTTP status code.
     *
     * @param status HTTP status code
     */
    public void setStatus(int status) {
        this.status = status;
    }

    /**
     * Returns the error title.
     *
     * @return error title
     */
    public String getError() {
        return error;
    }

    /**
     * Sets the error title.
     *
     * @param error error title
     */
    public void setError(String error) {
        this.error = error;
    }

    /**
     * Returns the detailed error message.
     *
     * @return detailed error message
     */
    public String getMessage() {
        return message;
    }

    /**
     * Sets the detailed error message.
     *
     * @param message detailed error message
     */
    public void setMessage(String message) {
        this.message = message;
    }
}