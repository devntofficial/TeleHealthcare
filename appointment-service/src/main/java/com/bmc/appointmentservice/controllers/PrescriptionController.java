package com.bmc.appointmentservice.controllers;

import com.bmc.appointmentservice.dto.PrescriptionDto;
import com.bmc.appointmentservice.exceptions.PaymentPendingException;
import com.bmc.appointmentservice.services.PrescriptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class PrescriptionController {
    private final PrescriptionService prescriptionService;

    @PostMapping("/prescriptions")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity savePrescriptions(@RequestHeader(value = "Authorization") String token, @RequestBody PrescriptionDto prescriptionDto) throws PaymentPendingException {
        prescriptionService.savePrescription(token, prescriptionDto);
        return new ResponseEntity(HttpStatus.OK);
    }
}
