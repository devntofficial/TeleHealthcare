package com.bmc.appointmentservice.services;

import com.bmc.appointmentservice.entities.AppointmentEntity;
import com.bmc.appointmentservice.entities.AvailabilityEntity;
import com.bmc.appointmentservice.repositories.AppointmentRepository;
import com.bmc.appointmentservice.repositories.AvailabilityRepository;
import com.bmc.paymentservice.dto.PaymentDto;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PaymentConsumerService {
    private final AppointmentRepository appointmentRepository;
    private final AvailabilityRepository availabilityRepository;

    @KafkaListener(topics = "${kafka.payment.consumer.topic}", groupId = "${kafka.group.id}", containerFactory = "paymentListenerContainerFactory")
    public void listen(@Payload PaymentDto paymentDto)
    {
        System.out.println("Received payment: " + paymentDto);
        AppointmentEntity appointment = appointmentRepository.findById(paymentDto.getAppointmentId()).orElse(null);
        if(appointment == null) {
            System.out.println("No appointment found with given id. Ignoring notification.");
        }
        else {
            //setting appointment status
            appointment.setStatus("Confirmed");
            appointmentRepository.save(appointment);

            //updating doctor's availability
            AvailabilityEntity availability = availabilityRepository
                    .findByDoctorIdAndAvailabilityDateAndTimeSlot(appointment.getDoctorId(), appointment.getAppointmentDate(), appointment.getTimeSlot());
            if(availability == null) {
                System.out.println("Could not find doctor's availability to update");
            }
            else {
                availability.setBooked(true);
                availabilityRepository.save(availability);
            }
        }
        System.out.println("Appointment updated with payment details successfully");
    }
}
