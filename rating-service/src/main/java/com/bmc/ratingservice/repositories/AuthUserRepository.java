package com.bmc.ratingservice.repositories;

import com.bmc.ratingservice.entities.AuthUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthUserRepository extends JpaRepository<AuthUserEntity, String> {
}
