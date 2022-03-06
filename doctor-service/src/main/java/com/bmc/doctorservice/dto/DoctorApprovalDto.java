package com.bmc.doctorservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DoctorApprovalDto {
    private String approvedBy;
    private String approverComments;
}
