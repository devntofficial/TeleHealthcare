package com.bmc.paymentservice.controllers;

import com.bmc.paymentservice.dto.PaymentDto;
import com.bmc.paymentservice.services.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class PaymentController {
    private final PaymentService paymentService;

    @PostMapping("/payments")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<PaymentDto> createPayment(@RequestParam String appointmentId) {
        PaymentDto response = paymentService.createPayment(appointmentId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
