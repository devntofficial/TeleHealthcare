package com.bmc.ratingservice.controllers;

import com.bmc.ratingservice.dto.RatingDto;
import com.bmc.ratingservice.services.RatingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class RatingController {
    private final RatingService ratingService;

    @PostMapping("/ratings")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity saveDoctorRatings(@RequestBody RatingDto ratingDto) {
        ratingService.saveDoctorRating(ratingDto);
        return new ResponseEntity(HttpStatus.OK);
    }

}
