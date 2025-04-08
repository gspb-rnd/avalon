package com.gspb.avalon.loan.presentation.rest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

/**
 * Error response for REST API.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponse {
    
    private int status;
    private String error;
    private Object message;
    
    /**
     * Constructor for error response with field validation errors.
     *
     * @param status HTTP status code
     * @param error Error type
     * @param fieldErrors Map of field validation errors
     */
    public ErrorResponse(int status, String error, Map<String, String> fieldErrors) {
        this.status = status;
        this.error = error;
        this.message = fieldErrors;
    }
    
    /**
     * Constructor for error response with string message.
     *
     * @param status HTTP status code
     * @param error Error type
     * @param message Error message
     */
    public ErrorResponse(int status, String error, String message) {
        this.status = status;
        this.error = error;
        this.message = message;
    }
}
