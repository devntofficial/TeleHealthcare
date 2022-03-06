package com.bmc.appointmentservice.services;

import com.bmc.appointmentservice.dto.DoctorDto;
import com.bmc.appointmentservice.dto.PrescriptionDto;
import com.bmc.appointmentservice.dto.UserDto;
import com.bmc.appointmentservice.entities.PrescriptionEntity;
import com.bmc.appointmentservice.exceptions.PaymentPendingException;
import com.bmc.appointmentservice.feign.DoctorServiceClient;
import com.bmc.appointmentservice.feign.UserServiceClient;
import com.bmc.appointmentservice.repositories.PrescriptionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PrescriptionService {
    private final PrescriptionRepository repository;
    private final AppointmentService appointmentService;
    private final DoctorServiceClient doctorServiceClient;
    private final UserServiceClient userServiceClient;
    private final PrescriptionProducerService kafkaProducerService;

    public void savePrescription(String token, PrescriptionDto prescriptionDto) throws PaymentPendingException {
        if(!appointmentService.getAppointmentById(prescriptionDto.getAppointmentId()).getStatus().equals("Confirmed")) {
            throw new PaymentPendingException();
        }

        PrescriptionEntity prescription = prescriptionDto.mapToEntity();
        DoctorDto doctorDto = doctorServiceClient.getDoctorById(token, prescription.getDoctorId());
        prescription.setDoctorName(doctorDto.getFirstName() + " " + doctorDto.getLastName());

        UserDto userDto = userServiceClient.getUserById(token, prescriptionDto.getUserId());
        prescription.setUserName(userDto.getFirstName() + " " + userDto.getLastName());
        prescription.setUserEmailId(userDto.getEmailId());

        kafkaProducerService.publish(prescription);
        repository.save(prescription);
    }
}
