package com.bmc.doctorservice.controllers;
import com.bmc.doctorservice.dto.DoctorApprovalDto;
import com.bmc.doctorservice.dto.DoctorDto;
import com.bmc.doctorservice.entities.DoctorEntity;
import com.bmc.doctorservice.services.DoctorService;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

import com.bmc.doctorservice.services.DocumentUploadService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@RestController
public class DoctorController {

    private final DoctorService doctorService;
    private final DocumentUploadService documentUploadService;

    @PostMapping("/doctors")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<DoctorEntity> collectDoctorInformation(@Valid @RequestBody DoctorDto doctorDto) throws Exception {
        DoctorEntity doctorEntity =  doctorService.createDoctor(doctorDto);
        return new ResponseEntity<>(doctorEntity, HttpStatus.CREATED);
    }

    @PostMapping("/doctors/{doctorId}/documents")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<String> uploadDoctorDocuments(@PathVariable String doctorId, @RequestParam MultipartFile[] files) throws IOException {
        for(MultipartFile file: files) {
            documentUploadService.uploadFiles(doctorId, file);
        }
        return new ResponseEntity<>("File(s) uploaded successfully", HttpStatus.OK);
    }

    @GetMapping("/doctors/{doctorId}")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<DoctorEntity> getDoctorById(@PathVariable String doctorId) throws Exception {
        DoctorEntity doctorEntity =  doctorService.getDoctorById(doctorId);
        return new ResponseEntity<>(doctorEntity, HttpStatus.OK);
    }

    @PutMapping("/doctors/{doctorId}/approve")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<DoctorEntity> approveDoctorRequest(@PathVariable String doctorId, @RequestBody DoctorApprovalDto doctorApprovalDto) throws Exception {
        DoctorEntity doctorEntity =  doctorService.approveDoctor(doctorId, doctorApprovalDto);
        return new ResponseEntity<>(doctorEntity, HttpStatus.OK);
    }

    @PutMapping("/doctors/{doctorId}/reject")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<DoctorEntity> rejectDoctorRequest(@PathVariable String doctorId, @RequestBody DoctorApprovalDto doctorApprovalDto) throws Exception {
        DoctorEntity doctorEntity =  doctorService.rejectDoctor(doctorId, doctorApprovalDto);
        return new ResponseEntity<>(doctorEntity, HttpStatus.OK);
    }

    @GetMapping("/doctors")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<DoctorEntity>> getTop20RatedDoctorByStatusAndSpeciality(@RequestParam String status, @RequestParam @Nullable String speciality) throws Exception {
        if(speciality == null || speciality.isBlank())
        {
            return new ResponseEntity<>(doctorService.getTop20RatedDoctorsByStatus(status), HttpStatus.OK);
        }
        return new ResponseEntity<>(doctorService.getTop20RatedDoctorsByStatusAndSpeciality(status, speciality), HttpStatus.OK);
    }
}
