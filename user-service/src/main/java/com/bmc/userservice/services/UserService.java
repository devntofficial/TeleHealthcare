package com.bmc.userservice.services;

import com.bmc.userservice.dto.UserDto;
import com.bmc.userservice.entities.UserEntity;
import com.bmc.userservice.exceptions.UserNotFoundException;
import com.bmc.userservice.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository repository;
    private final KafkaProducerService kafkaProducerService;

    public UserEntity createUser(UserDto userDto) {
        UserEntity user = userDto.mapToEntity();
        user.setCreatedDate(new Date());
        repository.save(user);
        kafkaProducerService.publish(user);
        return user;
    }

    public UserEntity getUserById(String userId) throws Exception {
        UserEntity user = repository.findById(userId).orElse(null);
        if(user == null) {
            throw new UserNotFoundException();
        }
        return user;
    }
}
