package com.bmc.appointmentservice.controllers;

import com.bmc.appointmentservice.dto.AppointmentDto;
import com.bmc.appointmentservice.entities.AppointmentEntity;
import com.bmc.appointmentservice.services.AppointmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class AppointmentController {
    private final AppointmentService appointmentService;

    @PostMapping("/appointments")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<String> bookAppointment(@RequestHeader(value = "Authorization") String token, @RequestBody AppointmentDto appointmentDto) {
        String appointmentId = appointmentService.bookAppointment(token, appointmentDto);
        return new ResponseEntity<>(appointmentId, HttpStatus.OK);
    }

    @GetMapping("/appointments/{appointmentId}")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<AppointmentEntity> getAppointmentById(@PathVariable String appointmentId) {
        AppointmentEntity appointment = appointmentService.getAppointmentById(appointmentId);
        return new ResponseEntity<>(appointment, HttpStatus.OK);
    }

    @GetMapping("/users/{userId}/appointments")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<AppointmentEntity>> getAppointmentsByUserId(@PathVariable String userId) {
        List<AppointmentEntity> appointments = appointmentService.getAppointmentsByUserId(userId);
        return new ResponseEntity<>(appointments, HttpStatus.OK);
    }
}
