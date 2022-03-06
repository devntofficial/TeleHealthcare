package com.bmc.ratingservice.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user")
public class AuthUserEntity {
    @Id
    @NotBlank(message = "User Name cannot be blank")
    @Column(name = "user_name")
    private String username;
    @NotBlank(message = "Password cannot be blank")
    @Column(name = "password")
    private String password;
    @NotBlank(message = "Role cannot be blank")
    @Column(name = "role")
    private String role;
}
