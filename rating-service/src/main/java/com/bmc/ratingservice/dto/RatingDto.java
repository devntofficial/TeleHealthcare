package com.bmc.ratingservice.dto;

import com.bmc.ratingservice.entities.RatingEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RatingDto {
    private String doctorId;
    private Double rating;
    private String comments;

    public RatingEntity mapToEntity() {
        RatingEntity rating = new RatingEntity();
        rating.setDoctorId(this.getDoctorId());
        rating.setRating(this.getRating());
        rating.setComments(this.getComments());
        return rating;
    }
}
