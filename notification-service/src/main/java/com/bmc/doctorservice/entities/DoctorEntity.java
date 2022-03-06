package com.bmc.doctorservice.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DoctorEntity {
    private String id;
    private String firstName;
    private String lastName;
    private String speciality;
    private Date dob;
    private String mobile;
    private String emailId;
    private String pan;
    private String status;
    private String approvedBy;
    private String approverComments;
    private Date registrationDate;
    private Date verificationDate;
    private Double averageRating;
}
