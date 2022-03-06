package com.bmc.appointmentservice.dto;

import com.bmc.appointmentservice.entities.AppointmentEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class AppointmentDto {
    private String doctorId;
    private String userId;
    private Date appointmentDate;
    private String timeSlot;

    public AppointmentEntity mapToEntity() {
        AppointmentEntity appointment = new AppointmentEntity();
        appointment.setAppointmentId(UUID.randomUUID().toString());
        appointment.setDoctorId(this.getDoctorId());
        appointment.setUserId(this.getUserId());
        appointment.setAppointmentDate(this.getAppointmentDate());
        appointment.setTimeSlot(this.getTimeSlot());
        appointment.setStatus("PendingPayment");
        appointment.setCreatedDate(new Date());
        return appointment;
    }
}
