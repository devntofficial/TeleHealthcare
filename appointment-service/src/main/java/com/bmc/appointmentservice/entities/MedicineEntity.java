package com.bmc.appointmentservice.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MedicineEntity {
    private String name;
    private String type;
    private String dosage;
    private String duration;
    private String frequency;
    private String remarks;
}