package com.bmc.doctorservice.services;

import com.bmc.doctorservice.dto.DoctorApprovalDto;
import com.bmc.doctorservice.dto.DoctorDto;
import com.bmc.doctorservice.entities.DoctorEntity;
import com.bmc.doctorservice.exceptions.DoctorNotFoundException;
import com.bmc.doctorservice.repositories.DoctorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@RequiredArgsConstructor
@Service
public class DoctorService {

    private final DoctorRepository repository;
    private final DoctorProducerService doctorProducerService;

    public DoctorEntity createDoctor(DoctorDto doctorDto) {
        DoctorEntity doctor = doctorDto.mapToEntity();
        doctor.setStatus("Pending");
        doctor.setRegistrationDate(new Date());
        repository.save(doctor);
        doctorProducerService.publish(doctor);
        return doctor;
    }

    public DoctorEntity approveDoctor(String doctorId, DoctorApprovalDto doctorApprovalDto) throws Exception {
        DoctorEntity doctor = getDoctorById(doctorId);
        doctor.setApprovedBy(doctorApprovalDto.getApprovedBy());
        doctor.setApproverComments(doctorApprovalDto.getApproverComments());
        doctor.setVerificationDate(new Date());
        doctor.setStatus("Active");
        repository.save(doctor);
        doctorProducerService.publish(doctor);
        return doctor;
    }

    public DoctorEntity rejectDoctor(String doctorId, DoctorApprovalDto doctorApprovalDto) throws Exception {
        DoctorEntity doctor = getDoctorById(doctorId);
        doctor.setApprovedBy(doctorApprovalDto.getApprovedBy());
        doctor.setApproverComments(doctorApprovalDto.getApproverComments());
        doctor.setVerificationDate(new Date());
        doctor.setStatus("Rejected");
        doctorProducerService.publish(doctor);
        repository.save(doctor);
        return doctor;
    }

    public DoctorEntity getDoctorById(String doctorId) throws Exception {
        DoctorEntity doctor = repository.findById(doctorId).orElse(null);
        if(doctor == null) {
            throw new DoctorNotFoundException();
        }
        return doctor;
    }

    public List<DoctorEntity> getTop20RatedDoctorsByStatus(String status)
    {
        return repository.findTop20ByStatusOrderByAverageRatingDesc(status);
    }

    public List<DoctorEntity> getTop20RatedDoctorsByStatusAndSpeciality(String status, String speciality)
    {
        return repository.findTop20ByStatusAndSpecialityOrderByAverageRatingDesc(status, speciality);
    }
}
