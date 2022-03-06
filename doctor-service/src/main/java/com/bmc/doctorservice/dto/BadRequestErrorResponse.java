package com.bmc.doctorservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BadRequestErrorResponse {
    private String errorCode;
    private String errorMessage;
    private List<String> errorFields;
}
