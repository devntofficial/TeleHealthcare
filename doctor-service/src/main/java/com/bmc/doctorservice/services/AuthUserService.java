package com.bmc.doctorservice.services;

import com.bmc.doctorservice.dto.UserPrincipal;
import com.bmc.doctorservice.entities.AuthUserEntity;
import com.bmc.doctorservice.repositories.AuthUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class AuthUserService {
    private final AuthUserRepository repository;

    public UserPrincipal loadUserByUsername(String id) throws UsernameNotFoundException {
        AuthUserEntity user = repository.findById(id)
                .orElseThrow(() -> new UsernameNotFoundException("User name " + id + " does not exist"));
        return UserPrincipal.create(user);
    }

    public List<AuthUserEntity> getAllUsers() {
        return repository.findAll();
    }
}
