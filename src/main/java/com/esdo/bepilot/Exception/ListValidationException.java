package com.esdo.bepilot.Exception;

import lombok.Data;
import org.springframework.stereotype.Component;

import javax.validation.ValidationException;
import java.util.ArrayList;
import java.util.List;

@Data
@Component
public class ListValidationException extends RuntimeException {
    private List<ValidationException> validationExceptions;

    public ListValidationException() {
        validationExceptions = new ArrayList<>();
    }
}