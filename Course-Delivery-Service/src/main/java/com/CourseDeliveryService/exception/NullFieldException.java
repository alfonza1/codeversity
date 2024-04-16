package com.CourseDeliveryService.exception;

public class NullFieldException extends RuntimeException {
    public NullFieldException(String fieldName) {
        super("The field '" + fieldName + "' cannot be null.");
    }
}
