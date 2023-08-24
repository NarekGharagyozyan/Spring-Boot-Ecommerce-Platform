package com.smartCode.ecommerce.exceptions;

public class PageRequestValidationException extends RuntimeException {
    public PageRequestValidationException(String message) {
        super(message);
    }
}
