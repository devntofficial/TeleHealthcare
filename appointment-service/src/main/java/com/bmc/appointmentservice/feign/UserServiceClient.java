package com.bmc.appointmentservice.feign;

import com.bmc.appointmentservice.dto.UserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "user-service", url = "${user.service.url}")
public interface UserServiceClient {
    @GetMapping("/users/{userId}")
    public UserDto getUserById(@RequestHeader(value = "Authorization") String token, @PathVariable String userId);
}
