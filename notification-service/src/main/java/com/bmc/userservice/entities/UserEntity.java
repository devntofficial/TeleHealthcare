package com.bmc.userservice.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity {
    private String id;
    private String firstName;
    private String lastName;
    private Date dob;
    private String mobile;
    private String emailId;
    private Date createdDate;
}
