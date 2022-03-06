package com.bmc.doctorservice.dto;

import com.bmc.doctorservice.entities.DoctorEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DoctorDto {
    @NotBlank
    @Pattern(regexp = "(^[a-zA-z]{1,20})")
    private String firstName;

    @NotBlank
    @Pattern(regexp = "(^[a-zA-z]{1,20})")
    private String lastName;

    @NotNull
    private Date dob;

    @NotBlank
    @Email
    private String emailId;

    @NotBlank
    @Pattern(regexp = "(^[0-9]{10})")
    private String mobile;

    @NotBlank
    @Pattern(regexp = "(^[a-zA-Z0-9]{10})")
    private String pan;

    private String speciality;

    public DoctorEntity mapToEntity()
    {
        DoctorEntity entity = new DoctorEntity();
        entity.setFirstName(this.getFirstName());
        entity.setLastName(this.getLastName());
        entity.setDob(this.getDob());
        entity.setEmailId(this.getEmailId());
        entity.setMobile(this.getMobile());
        entity.setPan(this.getPan());
        entity.setSpeciality(this.getSpeciality() == null || this.getSpeciality().isBlank() ? "General Physician" : this.getSpeciality());
        return entity;
    }
}
