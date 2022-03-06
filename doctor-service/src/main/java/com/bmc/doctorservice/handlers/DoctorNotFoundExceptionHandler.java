package com.bmc.doctorservice.handlers;

import com.bmc.doctorservice.dto.BadRequestErrorResponse;
import com.bmc.doctorservice.exceptions.DoctorNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class DoctorNotFoundExceptionHandler {

    private final String errorCode = "ERR_RESOURCE_NOT_FOUND";
    private final String errorMessage = "Requested resource is not available";

    @ExceptionHandler(DoctorNotFoundException.class)
    public ResponseEntity<BadRequestErrorResponse> handleDoctorNotFoundException(DoctorNotFoundException ex) {
        BadRequestErrorResponse response = new BadRequestErrorResponse(errorCode, errorMessage, null);
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
}
