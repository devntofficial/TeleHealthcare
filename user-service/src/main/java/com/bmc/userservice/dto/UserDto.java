package com.bmc.userservice.dto;

import com.bmc.userservice.entities.UserEntity;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Date;

public class UserDto {
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

    public UserEntity mapToEntity()
    {
        UserEntity entity = new UserEntity();
        entity.setFirstName(this.getFirstName());
        entity.setLastName(this.getLastName());
        entity.setDob(this.getDob());
        entity.setEmailId(this.getEmailId());
        entity.setMobile(this.getMobile());
        return entity;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }
}
