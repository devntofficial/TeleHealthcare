package com.bmc.appointmentservice.controllers;

import com.bmc.appointmentservice.dto.AvailabilityDto;
import com.bmc.appointmentservice.dto.AvailabilityResponseDto;
import com.bmc.appointmentservice.services.AvailabilityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class AvailabilityController {

    private final AvailabilityService availabilityService;

    @PostMapping("/doctor/{doctorId}/availability")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity saveDoctorAvailability(@PathVariable String doctorId, @RequestBody AvailabilityDto availabilityDto) throws Exception {
        availabilityService.saveDoctorAvailability(doctorId, availabilityDto);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/doctor/{doctorId}/availability")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<AvailabilityResponseDto> saveDoctorAvailability(@PathVariable String doctorId) {
        AvailabilityResponseDto response = availabilityService.getDoctorAvailability(doctorId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
