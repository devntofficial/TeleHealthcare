package com.bmc.paymentservice.services;

import com.bmc.paymentservice.dto.PaymentDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class PaymentService {
    private final PaymentProducerService paymentProducerService;

    public PaymentDto createPayment(String appointmentId) {
        PaymentDto response = new PaymentDto();
        response.setId(UUID.randomUUID().toString());
        response.setCreateDate(new Date());
        response.setAppointmentId(appointmentId);

        paymentProducerService.publish(response);
        return response;
    }
}
