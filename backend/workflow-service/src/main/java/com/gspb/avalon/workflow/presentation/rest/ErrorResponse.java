package com.gspb.avalon.workflow.presentation.rest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
}
