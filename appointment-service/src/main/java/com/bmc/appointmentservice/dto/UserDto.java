package com.bmc.appointmentservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserDto {
    private String id;
    private String firstName;
    private String lastName;
    private String emailId;
}
