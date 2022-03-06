package com.bmc.appointmentservice.services;

import com.bmc.appointmentservice.dto.AppointmentDto;
import com.bmc.appointmentservice.dto.DoctorDto;
import com.bmc.appointmentservice.dto.UserDto;
import com.bmc.appointmentservice.entities.AppointmentEntity;
import com.bmc.appointmentservice.feign.DoctorServiceClient;
import com.bmc.appointmentservice.feign.UserServiceClient;
import com.bmc.appointmentservice.repositories.AppointmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class AppointmentService {
    private final AppointmentRepository repository;
    private final DoctorServiceClient doctorServiceClient;
    private final UserServiceClient userServiceClient;
    private final AppointmentProducerService kafkaProducerService;

    public String bookAppointment(String token, AppointmentDto appointmentDto) {
        AppointmentEntity appointment = appointmentDto.mapToEntity();

        DoctorDto doctorDto = doctorServiceClient.getDoctorById(token, appointmentDto.getDoctorId());
        appointment.setDoctorName(doctorDto.getFirstName() + " " + doctorDto.getLastName());

        UserDto userDto = userServiceClient.getUserById(token, appointmentDto.getUserId());
        appointment.setUserName(userDto.getFirstName() + " " + userDto.getLastName());
        appointment.setUserEmailId(userDto.getEmailId());

        repository.save(appointment);
        kafkaProducerService.publish(appointment);
        return appointment.getAppointmentId();
    }

    public AppointmentEntity getAppointmentById(String appointmentId) {
        AppointmentEntity appointment = repository.findById(appointmentId).orElse(null);
        return appointment;
    }

    public List<AppointmentEntity> getAppointmentsByUserId(String userId) {
        List<AppointmentEntity> appointments = repository.findByUserId(userId);
        return appointments;
    }
}
