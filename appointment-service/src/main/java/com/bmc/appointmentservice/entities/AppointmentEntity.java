package com.bmc.appointmentservice.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "appointments")
@Entity
public class AppointmentEntity {

    @Id
    @Column(name = "appointment_id")
    private String appointmentId;

    @Column(name = "appointment_date", nullable = true, columnDefinition = "DATE")
    private Date appointmentDate;

    @Column(name = "created_date", nullable = true, columnDefinition = "DATETIME")
    private Date createdDate;

    @Column(name = "doctor_id", nullable = true, length = 255)
    private String doctorId;

    @Column(name = "prior_medical_history", nullable = true, length = 255)
    private String priorMedicalHistory;

    @Column(name = "status", nullable = true, length = 255)
    private String status;

    @Column(name = "symptoms", nullable = true, length = 255)
    private String symptoms;

    @Column(name = "time_slot", nullable = true, length = 255)
    private String timeSlot;

    @Column(name = "user_id", nullable = true, length = 255)
    private String userId;

    @Column(name = "user_email_id", nullable = true, length = 255)
    private String userEmailId;

    @Column(name = "user_name", nullable = true, length = 255)
    private String userName;

    @Column(name = "doctor_name", nullable = true, length = 255)
    private String doctorName;
}
