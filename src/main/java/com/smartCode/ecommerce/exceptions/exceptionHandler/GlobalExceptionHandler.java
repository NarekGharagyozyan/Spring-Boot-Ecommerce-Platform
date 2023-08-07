package com.smartCode.ecommerce.exceptions.exceptionHandler;

import com.smartCode.ecommerce.exceptions.DuplicationException;
import com.smartCode.ecommerce.exceptions.ResourceNotFoundException;
import com.smartCode.ecommerce.exceptions.ValidationException;
import com.smartCode.ecommerce.exceptions.VerificationException;
import com.smartCode.ecommerce.util.exceptionHandlerResponse.ApiError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(code = HttpStatus.NOT_FOUND)
    public ResponseEntity<ApiError> notFoundException(HttpServletRequest req, ResourceNotFoundException e) {
        return buildResponse(HttpStatus.NOT_FOUND, e.getMessage(), req.getRequestURI());
    }

    @ExceptionHandler(ValidationException.class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public ResponseEntity<ApiError> validationException(HttpServletRequest req, ValidationException e) {
        return buildResponse(HttpStatus.BAD_REQUEST, e.getMessage(), req.getRequestURI());
    }

    @ExceptionHandler(VerificationException.class)
    @ResponseStatus(code = HttpStatus.UNAUTHORIZED)
    public ResponseEntity<ApiError> verificationException(HttpServletRequest req, VerificationException e) {
        return buildResponse(HttpStatus.UNAUTHORIZED, e.getMessage(), req.getRequestURI());
    }

    @ExceptionHandler(DuplicationException.class)
    @ResponseStatus(code = HttpStatus.CONFLICT)
    public ResponseEntity<ApiError> runtimeException(HttpServletRequest req, DuplicationException e) {
        return buildResponse(HttpStatus.CONFLICT, e.getMessage(), req.getRequestURI());
    }

//    @ExceptionHandler(RuntimeException.class)
//    @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
//    public ResponseEntity<ApiError> runtimeException(HttpServletRequest req, RuntimeException e) {
//        return buildResponse(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), req.getRequestURI());
//    }

    private ResponseEntity<ApiError> buildResponse(HttpStatus httpStatus, String message, String uri) {
        var errors = new HashMap<String, String>();
        errors.put("message", message);
        var apiError = new ApiError(httpStatus.value(), uri,errors);
        return ResponseEntity.status(httpStatus).body(apiError);
    }
}
