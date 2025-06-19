package com.main.proyectobdd2.repository;

import com.main.proyectobdd2.model.Review;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends MongoRepository<Review, Integer> {
    Review findById(ObjectId id);
    void deleteById(ObjectId id);
    List<Review> findByMovieId(ObjectId movieId);
}
