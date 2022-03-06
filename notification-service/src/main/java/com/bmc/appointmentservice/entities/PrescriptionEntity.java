package com.bmc.appointmentservice.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PrescriptionEntity {
    private String id;
    private String userId;
    private String userName;
    private String userEmailId;
    private String doctorId;
    private String doctorName;
    private String appointmentId;
    private String diagnosis;
    private List<MedicineEntity> medicineList;
}