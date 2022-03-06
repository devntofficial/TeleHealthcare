package com.bmc.appointmentservice.entities;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "Prescriptions")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PrescriptionEntity {
    @Id
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
