package com.bmc.appointmentservice.repositories;

import com.bmc.appointmentservice.entities.AuthUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthUserRepository extends JpaRepository<AuthUserEntity, String> {
}
