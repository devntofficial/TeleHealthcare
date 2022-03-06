package com.bmc.ratingservice.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class RatingEntity {
    private String id;
    private String doctorId;
    private Double rating;
}
