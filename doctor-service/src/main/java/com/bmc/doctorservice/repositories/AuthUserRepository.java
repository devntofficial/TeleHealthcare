package com.bmc.doctorservice.repositories;

import com.bmc.doctorservice.entities.AuthUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthUserRepository extends JpaRepository<AuthUserEntity, String> {
}
