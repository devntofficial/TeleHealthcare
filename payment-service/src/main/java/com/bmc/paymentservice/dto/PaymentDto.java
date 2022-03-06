package com.bmc.paymentservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PaymentDto {
    private String id;
    private String appointmentId;
    private Date createDate;
}
