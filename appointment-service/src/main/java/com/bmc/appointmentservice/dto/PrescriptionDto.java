package com.bmc.appointmentservice.dto;

import com.bmc.appointmentservice.entities.MedicineEntity;
import com.bmc.appointmentservice.entities.PrescriptionEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PrescriptionDto {
    private String appointmentId;
    private String doctorId;
    private String userId;
    private String diagnosis;
    private List<MedicineEntity> medicineList;

    public PrescriptionEntity mapToEntity() {
        PrescriptionEntity prescription = new PrescriptionEntity();
        prescription.setAppointmentId(this.getAppointmentId());
        prescription.setDoctorId(this.getDoctorId());
        prescription.setUserId(this.getUserId());
        prescription.setDiagnosis(this.getDiagnosis());
        prescription.setMedicineList(this.getMedicineList());
        return prescription;
    }
}
