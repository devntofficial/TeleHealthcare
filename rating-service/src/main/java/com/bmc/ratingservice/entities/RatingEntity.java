package com.bmc.ratingservice.entities;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "DoctorRatings")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RatingEntity {
    @Id
    private String id;
    private String DoctorId;
    private Double rating;
    private String comments;
}
