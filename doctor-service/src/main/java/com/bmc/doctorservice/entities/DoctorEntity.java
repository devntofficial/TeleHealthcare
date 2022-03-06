package com.bmc.doctorservice.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "Doctors")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DoctorEntity {

    @Id
    private String id;
    private String firstName;
    private String lastName;
    private String speciality;
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date dob;
    private String mobile;
    private String emailId;
    private String pan;
    private String status;
    private String approvedBy;
    private String approverComments;
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date registrationDate;
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date verificationDate;
    @JsonIgnore
    private Double averageRating;
}
