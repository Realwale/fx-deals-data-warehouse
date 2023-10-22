package com.progresoft.fxdealsdatawarehouse.exception;

import com.progresoft.fxdealsdatawarehouse.dto.response.FXDealResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;



@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<FXDealResponse> handleValidationExceptions(ValidationException ex) {
        FXDealResponse response = createErrorResponse(HttpStatus.BAD_REQUEST, ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<FXDealResponse> handleNotFoundExceptions(NotFoundException ex) {
        FXDealResponse response = createErrorResponse(HttpStatus.NOT_FOUND, ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DuplicateDealException.class)
    public ResponseEntity<FXDealResponse> handleNotFoundExceptions(DuplicateDealException ex) {
        FXDealResponse response = createErrorResponse(HttpStatus.CONFLICT, ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }

    private FXDealResponse createErrorResponse(HttpStatus status, String message) {
        FXDealResponse response = new FXDealResponse();
        response.setSuccess(false);
        response.setError(true);
        response.setData(message);
        response.setStatusCode(status.value());
        return response;
    }
}
