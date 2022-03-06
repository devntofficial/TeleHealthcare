package com.bmc.appointmentservice.services;

import com.bmc.appointmentservice.dto.AvailabilityDto;
import com.bmc.appointmentservice.dto.AvailabilityResponseDto;
import com.bmc.appointmentservice.entities.AvailabilityEntity;
import com.bmc.appointmentservice.repositories.AvailabilityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class AvailabilityService {

    private final AvailabilityRepository repository;

    public void saveDoctorAvailability(String doctorId, AvailabilityDto availabilityDto) throws ParseException {
        if(repository.existsByDoctorId(doctorId)) {
            List<AvailabilityEntity> existingAvailabilities = repository.findByDoctorId(doctorId);
            List<Long> existingIds = existingAvailabilities.stream().map(AvailabilityEntity::getId).collect(Collectors.toList());
            repository.deleteAllById(existingIds);
        }

        List<AvailabilityEntity> newAvailabilityEntity = new ArrayList<AvailabilityEntity>();
        for(Map.Entry<String, List<String>> entry : availabilityDto.getAvailabilityMap().entrySet()) {
            Date availabilityDate = new SimpleDateFormat("yyyy-MM-dd").parse(entry.getKey());
            for(String timeSlot : entry.getValue()) {
                AvailabilityEntity availability = new AvailabilityEntity();
                availability.setDoctorId(doctorId);
                availability.setAvailabilityDate(availabilityDate);
                availability.setBooked(false);
                availability.setTimeSlot(timeSlot);
                newAvailabilityEntity.add(availability);
            }
        }

        repository.saveAll(newAvailabilityEntity);
    }

    public AvailabilityResponseDto getDoctorAvailability(String doctorId) {
        AvailabilityResponseDto output = new AvailabilityResponseDto();
        output.setDoctorId(doctorId);
        List<AvailabilityEntity> doctorAvailabilities = repository.findByDoctorId(doctorId);

        if(doctorAvailabilities.isEmpty()) {
            return output;
        }

        Map<String, List<String>> availabilityMap = new HashMap<>();
        for(AvailabilityEntity availability : doctorAvailabilities) {
            String availabilityDate = new SimpleDateFormat("yyyy-MM-dd").format(availability.getAvailabilityDate());
            List<String> timeSlots;

            if(availabilityMap.containsKey(availabilityDate)) {
                timeSlots = availabilityMap.get(availabilityDate);
                timeSlots.add(availability.getTimeSlot());
                availabilityMap.replace(availabilityDate, timeSlots);
            }
            else {
                timeSlots = new ArrayList<>();
                timeSlots.add(availability.getTimeSlot());
                availabilityMap.put(availabilityDate, timeSlots);
            }
        }

        output.setAvailabilityMap(availabilityMap);
        return output;
    }


}
