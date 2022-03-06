package com.bmc.userservice.handlers;

import com.bmc.userservice.dto.BadRequestErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class ValidationExceptionHandler {

    private final String errorCode = "ERR_INVALID_INPUT";
    private final String errorMessage = "Invalid input. Parameter name: ";

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<BadRequestErrorResponse> handleValidationException(MethodArgumentNotValidException ex) {
        BadRequestErrorResponse response = new BadRequestErrorResponse(errorCode, errorMessage);

        List<String> errorFields = new ArrayList<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            errorFields.add(fieldName);
        });

        response.setErrorFields(errorFields);
        return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
    }
}
