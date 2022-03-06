package com.bmc.appointmentservice.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppointmentEntity {
    private String appointmentId;
    private Date appointmentDate;
    private Date createdDate;
    private String doctorId;
    private String priorMedicalHistory;
    private String status;
    private String symptoms;
    private String timeSlot;
    private String userId;
    private String userEmailId;
    private String userName;
    private String doctorName;
}
