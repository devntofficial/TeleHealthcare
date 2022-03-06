package com.bmc.appointmentservice.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "availability")
@Entity
public class AvailabilityEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "availability_date", nullable = true, columnDefinition = "DATE")
    private Date availabilityDate;

    @Column(name = "doctor_id", nullable = true, length = 255)
    private String doctorId;

    @Column(name = "is_booked", nullable = false, columnDefinition = "BIT")
    private boolean isBooked;

    @Column(name = "time_slot", nullable = true, length = 255)
    private String timeSlot;
}
