package com.bmc.userservice.repositories;

import com.bmc.userservice.entities.AuthUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthUserRepository extends JpaRepository<AuthUserEntity, String> {
}
