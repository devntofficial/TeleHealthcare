package com.bmc.ratingservice.repositories;

import com.bmc.ratingservice.entities.RatingEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RatingRepository extends MongoRepository<RatingEntity, String> {

}
