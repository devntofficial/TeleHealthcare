package com.bmc.paymentservice.repositories;

import com.bmc.paymentservice.entities.AuthUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthUserRepository extends JpaRepository<AuthUserEntity, String> {
}
