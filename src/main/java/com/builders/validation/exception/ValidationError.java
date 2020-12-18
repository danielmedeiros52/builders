package com.builders.validation.exception;

import java.util.ArrayList;
import java.util.List;
import lombok.Getter;

@Getter
public class ValidationError extends StandardError {

    private List<FieldMessage> errors = new ArrayList<>();

    public ValidationError(Integer status, String errorMessage, Long instantOfError) {
        super(status, errorMessage, instantOfError);
    }

    public ValidationError(Integer status, String errorMessage, Long instantOfError, String fieldName, String message) {
        super(status, errorMessage, instantOfError);
        errors.add(new FieldMessage(fieldName, message));
    }

    public void addError(String fieldName, String message) {
        errors.add(new FieldMessage(fieldName, message));
    }
}
