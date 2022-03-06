package com.bmc.userservice.controllers;

import com.bmc.userservice.dto.UserDto;
import com.bmc.userservice.entities.UserEntity;
import com.bmc.userservice.services.DocumentUploadService;
import com.bmc.userservice.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;

@RequiredArgsConstructor
@RestController
public class UserController {

    private final UserService userService;
    private final DocumentUploadService documentUploadService;

    @PostMapping("/users")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<UserEntity> collectUserInformation(@Valid @RequestBody UserDto userDto) {
        UserEntity userEntity =  userService.createUser(userDto);
        return new ResponseEntity<>(userEntity, HttpStatus.CREATED);
    }

    @GetMapping("/users/{userId}")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<UserEntity> getUserById(@PathVariable String userId) throws Exception {
        UserEntity userEntity =  userService.getUserById(userId);
        return new ResponseEntity<>(userEntity, HttpStatus.OK);
    }

    @PostMapping("/users/{userId}/documents")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<String> uploadUserDocuments(@PathVariable String userId, @RequestParam MultipartFile[] files) throws IOException {
        for(MultipartFile file: files) {
            documentUploadService.uploadFiles(userId, file);
        }
        return new ResponseEntity<>("File(s) uploaded successfully", HttpStatus.OK);
    }
}
