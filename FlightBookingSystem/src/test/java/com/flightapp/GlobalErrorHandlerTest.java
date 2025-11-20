package com.flightapp;

import org.junit.jupiter.api.Test;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GlobalErrorHandlerTest {

    @Test
    void testHandleValidationException() {
        // Create a dummy object for validation
        Object target = new Object();
        BeanPropertyBindingResult bindingResult = new BeanPropertyBindingResult(target, "objectName");

        // Add some field errors
        bindingResult.addError(new FieldError("objectName", "airline", "Airline is mandatory"));
        bindingResult.addError(new FieldError("objectName", "fromPlace", "FromPlace is mandatory"));

        // Wrap in MethodArgumentNotValidException
        MethodArgumentNotValidException ex = new MethodArgumentNotValidException(null, bindingResult);

        // Call your GlobalErrorHandler
        GlobalErrorHandler handler = new GlobalErrorHandler();
        Map<String, String> errors = handler.handleValidationException(ex);

        // Verify the result
        assertEquals(2, errors.size());
        assertEquals("Airline is mandatory", errors.get("airline"));
        assertEquals("FromPlace is mandatory", errors.get("fromPlace"));
    }
}
