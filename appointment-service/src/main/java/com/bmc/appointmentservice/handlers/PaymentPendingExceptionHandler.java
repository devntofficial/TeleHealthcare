package com.bmc.appointmentservice.handlers;

import com.bmc.appointmentservice.dto.BadRequestErrorResponse;
import com.bmc.appointmentservice.exceptions.PaymentPendingException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class PaymentPendingExceptionHandler {
    private final String errorCode = "ERR_PAYMENT_PENDING";
    private final String errorMessage = "Prescription cannot be issued since the payment status is pending";

    @ExceptionHandler(PaymentPendingException.class)
    public ResponseEntity<BadRequestErrorResponse> handlePaymentPendingException(PaymentPendingException ex) {
        BadRequestErrorResponse response = new BadRequestErrorResponse(errorCode, errorMessage);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
