package com.bmc.userservice.handlers;

import com.bmc.userservice.dto.BadRequestErrorResponse;
import com.bmc.userservice.exceptions.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class UserNotFoundExceptionHandler {

    private final String errorCode = "ERR_RESOURCE_NOT_FOUND";
    private final String errorMessage = "Requested resource is not available";

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<BadRequestErrorResponse> handleUserNotFoundException(UserNotFoundException ex) {
        BadRequestErrorResponse response = new BadRequestErrorResponse(errorCode, errorMessage);
        return new ResponseEntity(response, HttpStatus.NOT_FOUND);
    }
}
