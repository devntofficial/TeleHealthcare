package com.bmc.appointmentservice.feign;

import com.bmc.appointmentservice.dto.DoctorDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "doctor-service", url = "${doctor.service.url}")
public interface DoctorServiceClient {

    @GetMapping("/doctors/{doctorId}")
    public DoctorDto getDoctorById(@RequestHeader(value = "Authorization") String token, @PathVariable String doctorId);

}
